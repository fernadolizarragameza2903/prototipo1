class GranMenteUi {
  constructor() {
    this.elements = {
      menuToggle: document.querySelector(".menu-toggle"),
      mainNav: document.querySelector(".main-nav"),
      userToggle: document.querySelector("#userToggle"),
      userDropdown: document.querySelector("#userDropdown"),
      logoutButtons: document.querySelectorAll(".js-logout-trigger"),
      logoutOverlay: document.querySelector("#logoutOverlay"),
      closeLogoutModal: document.querySelector("#closeLogoutModal"),
      cancelLogout: document.querySelector("#cancelLogout"),
      chatForm: document.querySelector("#chatForm"),
      chatInput: document.querySelector("#chatInput"),
      chatMessages: document.querySelector("#chatMessages"),
      micButton: document.querySelector("#micButton"),
      micStatus: document.querySelector("#micStatus")
    };
  }

  init() {
    this.setupMenu();
    this.setupUserMenu();
    this.setupLogoutModal();
    this.setupRouteTabs();
    this.setupChat();
    this.setupCounters();
    this.setupReveal();
    this.setupParticles();
    this.setupVoice();
  }

  setupMenu() {
    const { menuToggle, mainNav } = this.elements;
    if (!menuToggle || !mainNav) return;
    menuToggle.addEventListener("click", () => {
      const isOpen = mainNav.classList.toggle("open");
      menuToggle.setAttribute("aria-expanded", String(isOpen));
    });
  }

  setupUserMenu() {
    const { userToggle, userDropdown } = this.elements;
    if (!userToggle || !userDropdown) return;
    userToggle.addEventListener("click", (event) => {
      event.stopPropagation();
      const isOpen = userDropdown.classList.toggle("open");
      userToggle.setAttribute("aria-expanded", String(isOpen));
    });
    document.addEventListener("click", (event) => {
      if (!userToggle.contains(event.target) && !userDropdown.contains(event.target)) {
        userDropdown.classList.remove("open");
        userToggle.setAttribute("aria-expanded", "false");
      }
    });
  }

  setupLogoutModal() {
    const { logoutButtons, logoutOverlay, closeLogoutModal, cancelLogout, userDropdown, userToggle } = this.elements;
    if (!logoutButtons.length || !logoutOverlay) return;
    const openModal = () => {
      userDropdown?.classList.remove("open");
      userToggle?.setAttribute("aria-expanded", "false");
      logoutOverlay.hidden = false;
      requestAnimationFrame(() => logoutOverlay.classList.add("open"));
    };
    const closeModal = () => {
      logoutOverlay.classList.remove("open");
      setTimeout(() => {
        logoutOverlay.hidden = true;
      }, 200);
    };
    logoutButtons.forEach((button) => button.addEventListener("click", openModal));
    closeLogoutModal?.addEventListener("click", closeModal);
    cancelLogout?.addEventListener("click", closeModal);
    logoutOverlay.addEventListener("click", (event) => {
      if (event.target === logoutOverlay) closeModal();
    });
    document.addEventListener("keydown", (event) => {
      if (event.key === "Escape" && logoutOverlay.classList.contains("open")) closeModal();
    });
  }

  setupRouteTabs() {
    const buttons = document.querySelectorAll(".stem-hamburger");
    const panels = document.querySelectorAll(".stem-route-panel");
    if (!buttons.length || !panels.length) return;
    buttons.forEach((button) => {
      button.addEventListener("click", () => {
        const route = button.dataset.stem;
        buttons.forEach((item) => {
          item.classList.remove("active");
          item.setAttribute("aria-pressed", "false");
        });
        panels.forEach((panel) => {
          panel.hidden = panel.dataset.route !== route;
        });
        button.classList.add("active");
        button.setAttribute("aria-pressed", "true");
      });
    });
  }

  setupChat() {
    const { chatForm, chatInput } = this.elements;
    if (!chatForm || !chatInput) return;
    chatForm.addEventListener("submit", async (event) => {
      event.preventDefault();
      const text = chatInput.value.trim();
      if (!text) return;
      this.addChatMessage(text, true);
      chatInput.value = "";
      try {
        const response = await fetch("/api/ai/chat", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            [this.csrfHeader()]: this.csrfToken()
          },
          body: JSON.stringify({ message: text })
        });
        const data = await response.json();
        const reply = data.message || "No pude responder en este momento.";
        this.addChatMessage(reply, false);
      } catch {
        const err = "No pude conectar con el servicio de IA. Intenta otra vez.";
        this.addChatMessage(err, false);
      }
    });
  }

  setupVoice() {
    const { micButton, micStatus, chatInput, chatForm } = this.elements;
    const SpeechRec = window.SpeechRecognition || window.webkitSpeechRecognition;
    if (!micButton) return;
    if (!SpeechRec) {
      micButton.disabled = true;
      micButton.title = "Micrófono no soportado";
      if (micStatus) micStatus.textContent = "Reconocimiento de voz no soportado en este navegador.";
      return;
    }
    const recognition = new SpeechRec();
    recognition.lang = "es-PE";
    recognition.interimResults = false;
    recognition.maxAlternatives = 1;
    let listening = false;
    micButton.addEventListener("click", () => {
      if (!listening) {
        recognition.start();
      } else {
        recognition.stop();
      }
    });
    recognition.addEventListener("start", () => {
      listening = true;
      micButton.classList.add("listening");
      if (micStatus) micStatus.textContent = "Escuchando... habla ahora.";
    });
    recognition.addEventListener("end", () => {
      listening = false;
      micButton.classList.remove("listening");
      if (micStatus) micStatus.textContent = "Listo.";
    });
    recognition.addEventListener("result", (event) => {
      const transcript = Array.from(event.results).map(r => r[0].transcript).join("");
      if (chatInput) chatInput.value = transcript;
      if (micStatus) micStatus.textContent = "Texto reconocido: " + transcript;
      if (chatForm) {
        setTimeout(() => chatForm.requestSubmit(), 0);
      }
    });
    recognition.addEventListener("error", (ev) => {
      if (micStatus) {
        if (ev.error === "not-allowed") {
          micStatus.textContent = "Permiso de micrófono denegado. Habilita el acceso al micrófono en el navegador.";
        } else {
          micStatus.textContent = "Error de reconocimiento: " + (ev.error || "desconocido");
        }
      }
    });
    // voice recognition setup complete
  }

  setupReveal() {
    const observer = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) entry.target.classList.add("visible");
      });
    }, { threshold: 0.16 });
    document.querySelectorAll(".reveal").forEach((element) => observer.observe(element));
  }

  setupCounters() {
    const counters = document.querySelectorAll("[data-counter]");
    if (!counters.length) return;
    const observer = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (!entry.isIntersecting || entry.target.dataset.done) return;
        const target = Number(entry.target.dataset.counter);
        const start = performance.now();
        const tick = (now) => {
          const progress = Math.min((now - start) / 1100, 1);
          entry.target.textContent = Math.floor(progress * target);
          if (progress < 1) requestAnimationFrame(tick);
        };
        entry.target.dataset.done = "true";
        requestAnimationFrame(tick);
      });
    }, { threshold: 0.4 });
    counters.forEach((counter) => observer.observe(counter));
  }

  addChatMessage(text, fromUser) {
    const { chatMessages } = this.elements;
    if (!chatMessages) return;
    const wrapper = document.createElement('div');
    wrapper.className = fromUser ? 'user-message' : 'bot-message';
    wrapper.textContent = text;
    chatMessages.appendChild(wrapper);
    chatMessages.scrollTop = chatMessages.scrollHeight;
  }

  setupParticles() {
    const canvas = document.querySelector("#particles");
    if (!canvas) return;
    const context = canvas.getContext("2d");
    const particles = Array.from({ length: 70 }, () => ({
      x: Math.random(),
      y: Math.random(),
      radius: Math.random() * 1.8 + 0.5,
      speed: Math.random() * 0.22 + 0.08,
      alpha: Math.random() * 0.45 + 0.2
    }));
    const resize = () => {
      canvas.width = window.innerWidth * window.devicePixelRatio;
      canvas.height = window.innerHeight * window.devicePixelRatio;
      context.setTransform(window.devicePixelRatio, 0, 0, window.devicePixelRatio, 0, 0);
    };
    const draw = () => {
      context.clearRect(0, 0, window.innerWidth, window.innerHeight);
      particles.forEach((particle) => {
        particle.y -= particle.speed / window.innerHeight;
        if (particle.y < -0.02) {
          particle.y = 1.02;
          particle.x = Math.random();
        }
        context.beginPath();
        context.arc(particle.x * window.innerWidth, particle.y * window.innerHeight, particle.radius, 0, Math.PI * 2);
        context.fillStyle = `rgba(77, 232, 255, ${particle.alpha})`;
        context.fill();
      });
      requestAnimationFrame(draw);
    };
    resize();
    draw();
    window.addEventListener("resize", resize);
  }

  csrfHeader() {
    const meta = document.querySelector('meta[name="_csrf_header"]');
    return meta ? meta.getAttribute('content') : 'X-CSRF-TOKEN';
  }

  csrfToken() {
    const meta = document.querySelector('meta[name="_csrf"]');
    return meta ? meta.getAttribute('content') : '';
  }
}

new GranMenteUi().init();
