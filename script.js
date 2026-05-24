const STORAGE_KEY = "granMenteProgress";

const questions = [
  {
    text: "Que actividad te gusta mas?",
    options: [
      { label: "Crear videojuegos", key: "programacion" },
      { label: "Resolver problemas", key: "logica" },
      { label: "Hacer experimentos", key: "ciencia" },
      { label: "Construir cosas", key: "ingenieria" }
    ]
  },
  {
    text: "Que tema te interesa mas?",
    options: [
      { label: "Tecnologia", key: "programacion" },
      { label: "Espacio", key: "espacio" },
      { label: "Robots", key: "robotica" },
      { label: "Ciencia", key: "ciencia" }
    ]
  },
  {
    text: "Como prefieres aprender?",
    options: [
      { label: "Videos", key: "visual" },
      { label: "Juegos", key: "programacion" },
      { label: "Proyectos", key: "ingenieria" },
      { label: "Experimentos", key: "ciencia" }
    ]
  },
  {
    text: "Que habilidad te representa mas?",
    options: [
      { label: "Creatividad", key: "programacion" },
      { label: "Logica", key: "logica" },
      { label: "Curiosidad", key: "ciencia" },
      { label: "Liderazgo", key: "ingenieria" }
    ]
  },
  {
    text: "Que te gustaria crear?",
    options: [
      { label: "Una app", key: "programacion" },
      { label: "Un robot", key: "robotica" },
      { label: "Un laboratorio", key: "ciencia" },
      { label: "Un videojuego", key: "programacion" }
    ]
  }
];

const profiles = {
  programacion: {
    icon: "PC",
    name: "Programador del Futuro",
    description: "Tu creatividad digital puede convertirse en apps, videojuegos e inteligencia artificial.",
    careers: ["Ingenieria de Software", "Inteligencia Artificial", "Ciencia de Datos", "Diseno de Videojuegos"],
    game: "Roblox Studio",
    gameCategory: "Programacion y videojuegos",
    gameDescription: "Permite crear mundos, scripts y experiencias interactivas mientras aprende logica.",
    video: "Como crear videojuegos",
    videoDescription: "Introduccion practica para entender como se empieza a programar juegos.",
    videoUrl: "https://www.youtube.com/results?search_query=como+crear+videojuegos+para+principiantes",
    message: "Tienes potencial para crear soluciones tecnologicas e innovadoras."
  },
  logica: {
    icon: "LG",
    name: "Explorador Tecnologico",
    description: "Disfrutas analizar patrones, resolver retos y encontrar respuestas con precision.",
    careers: ["Ciencia de Datos", "Ingenieria de Software", "Inteligencia Artificial", "Ciberseguridad"],
    game: "Portal 2",
    gameCategory: "Logica y resolucion de problemas",
    gameDescription: "Entrena pensamiento espacial, causa y efecto, y solucion de retos complejos.",
    video: "Que es la Inteligencia Artificial",
    videoDescription: "Video recomendado para conectar logica, algoritmos y tecnologia inteligente.",
    videoUrl: "https://www.youtube.com/results?search_query=que+es+la+inteligencia+artificial+para+estudiantes",
    message: "Tu pensamiento logico puede ayudarte a resolver problemas reales."
  },
  ciencia: {
    icon: "CI",
    name: "Cientifico Creativo",
    description: "Tu curiosidad te impulsa a experimentar, observar y descubrir como funciona el mundo.",
    careers: ["Biotecnologia", "Ciencias Ambientales", "Ciencia de Datos", "Ingenieria Quimica"],
    game: "Minecraft Education",
    gameCategory: "Ciencia y experimentacion",
    gameDescription: "Ayuda a explorar ciencia, creatividad y retos educativos mediante actividades interactivas.",
    video: "Experimentos STEM para estudiantes",
    videoDescription: "Ideas de experimentos para aprender ciencia de forma visual y practica.",
    videoUrl: "https://www.youtube.com/results?search_query=experimentos+stem+para+estudiantes",
    message: "Tu curiosidad puede convertirse en descubrimientos utiles."
  },
  ingenieria: {
    icon: "IN",
    name: "Ingeniero Innovador",
    description: "Te gusta construir, liderar proyectos y transformar ideas en soluciones tangibles.",
    careers: ["Mecatronica", "Ingenieria Aeroespacial", "Robotica", "Ingenieria Industrial"],
    game: "Scrap Mechanic",
    gameCategory: "Construccion e ingenieria",
    gameDescription: "Permite crear mecanismos, probar estructuras y pensar como un ingeniero.",
    video: "Introduccion a la ingenieria y robotica",
    videoDescription: "Explica como se conectan diseno, maquinas y solucion de problemas.",
    videoUrl: "https://www.youtube.com/results?search_query=introduccion+a+la+ingenieria+y+robotica+para+estudiantes",
    message: "Tu talento para construir puede crear tecnologias utiles."
  },
  robotica: {
    icon: "RB",
    name: "Constructor Robotico",
    description: "Te atrae unir mecanica, electronica y programacion para crear maquinas inteligentes.",
    careers: ["Robotica", "Mecatronica", "Inteligencia Artificial", "Automatizacion Industrial"],
    game: "Human Resource Machine",
    gameCategory: "Algoritmos y automatizacion",
    gameDescription: "Convierte instrucciones y logica en soluciones, como una introduccion a programar maquinas.",
    video: "Introduccion a la Robotica",
    videoDescription: "Recurso para entender sensores, actuadores y programacion de robots.",
    videoUrl: "https://www.youtube.com/results?search_query=introduccion+a+la+robotica+para+estudiantes",
    message: "Puedes combinar imaginacion y tecnologia para crear robots."
  },
  espacio: {
    icon: "SP",
    name: "Explorador Espacial",
    description: "Tu interes por el universo puede llevarte hacia fisica, ingenieria y tecnologia aeroespacial.",
    careers: ["Ingenieria Aeroespacial", "Astrofisica", "Ciencia de Datos", "Ingenieria de Software"],
    game: "Kerbal Space Program",
    gameCategory: "Espacio, fisica e ingenieria",
    gameDescription: "Permite disenar cohetes, probar misiones y aprender principios de vuelo espacial.",
    video: "Como funciona la NASA",
    videoDescription: "Contenido para descubrir misiones espaciales y profesiones STEM conectadas al espacio.",
    videoUrl: "https://www.youtube.com/results?search_query=como+funciona+la+NASA+para+estudiantes",
    message: "Tu fascinacion por el espacio puede abrir una ruta STEM avanzada."
  }
};

const programmingPath = [
  {
    tag: "videojuegos",
    title: "Crear tu primer videojuego 2D",
    level: "Inicial",
    why: "Ideal si le interesa programar construyendo algo jugable.",
    steps: ["Variables y condicionales", "Movimiento de personaje", "Diseno de niveles simples"]
  },
  {
    tag: "app",
    title: "Disenar una app escolar",
    level: "Inicial",
    why: "Conecta creatividad, solucion de problemas y tecnologia util.",
    steps: ["Prototipo en papel", "Pantallas con HTML y CSS", "Botones con JavaScript"]
  },
  {
    tag: "robotica",
    title: "Programar un robot basico",
    level: "Intermedio",
    why: "Perfecto si le interesan robots, sensores y automatizacion.",
    steps: ["Instrucciones secuenciales", "Sensores simulados", "Respuestas automaticas"]
  },
  {
    tag: "logica",
    title: "Retos de logica para programadores",
    level: "Inicial",
    why: "Fortalece la forma de pensar que usan los desarrolladores.",
    steps: ["Resolver patrones", "Crear algoritmos simples", "Depurar errores"]
  },
  {
    tag: "ia",
    title: "Mini asistente con IA simulada",
    level: "Intermedio",
    why: "Ayuda a entender como una app puede responder con reglas inteligentes.",
    steps: ["Lista de respuestas", "Palabras clave", "Recomendaciones concisas"]
  }
];

const miniProjects = [
  {
    title: "Calculadora STEM",
    area: "programacion",
    level: "Inicial",
    time: "30 min",
    description: "Crear una calculadora visual con HTML, CSS y JavaScript.",
    deliverable: "Una pagina que suma, resta y muestra resultados."
  },
  {
    title: "Mini videojuego de reflejos",
    area: "programacion",
    level: "Inicial",
    time: "45 min",
    description: "Un boton aparece en lugares distintos y el alumno gana puntos al hacer clic.",
    deliverable: "Juego con puntos, tiempo y reinicio."
  },
  {
    title: "Chatbot de reglas",
    area: "ia",
    level: "Intermedio",
    time: "50 min",
    description: "Crear un asistente que responde segun palabras clave.",
    deliverable: "Chat simple que recomienda una ruta STEM."
  },
  {
    title: "Simulador de robot",
    area: "robotica",
    level: "Intermedio",
    time: "60 min",
    description: "Mover un robot en una cuadricula usando botones de direccion.",
    deliverable: "Robot visual con instrucciones basicas."
  },
  {
    title: "Dashboard de habitos",
    area: "datos",
    level: "Intermedio",
    time: "55 min",
    description: "Registrar horas de estudio y mostrar progreso con barras.",
    deliverable: "Panel con metricas y recomendaciones."
  },
  {
    title: "Mision espacial interactiva",
    area: "espacio",
    level: "Inicial",
    time: "40 min",
    description: "Elegir combustible, destino y ver si la mision despega.",
    deliverable: "Simulador de decisiones con resultado final."
  }
];

function loadProgress() {
  const fallback = { surveys: 0, xp: 720, scores: {}, answers: [], history: [], lastProfile: "programacion" };
  try {
    return { ...fallback, ...JSON.parse(localStorage.getItem(STORAGE_KEY)) };
  } catch {
    return fallback;
  }
}

function saveProgress(progress) {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(progress));
}

function getMaturity(surveys) {
  if (surveys >= 8) return { name: "Madurez Experta", level: 5, detail: "Recomendaciones muy concretas por patron repetido." };
  if (surveys >= 5) return { name: "Madurez Avanzada", level: 4, detail: "La plataforma detecta gustos dominantes con mayor precision." };
  if (surveys >= 3) return { name: "Madurez Intermedia", level: 3, detail: "Ya existen preferencias suficientes para recomendar mejor." };
  if (surveys >= 1) return { name: "Madurez Inicial", level: 2, detail: "Primeras senales de interes vocacional." };
  return { name: "Sin diagnostico", level: 1, detail: "Completa encuestas para mejorar la precision." };
}

function getTopKey(scores) {
  const ranked = Object.entries(scores || {}).sort((a, b) => b[1] - a[1]);
  const key = ranked[0]?.[0] || "programacion";
  return key === "visual" ? "programacion" : key;
}

function getProgrammingRecommendations(answers = []) {
  const labels = answers.map((answer) => answer.answer.toLowerCase()).join(" ");
  const keys = answers.map((answer) => answer.key);
  const selected = programmingPath.filter((item) => (
    keys.includes(item.tag) ||
    labels.includes(item.tag) ||
    (item.tag === "ia" && keys.includes("programacion"))
  ));
  const fallback = programmingPath.filter((item) => ["videojuegos", "app", "logica"].includes(item.tag));
  return (selected.length ? selected : fallback).slice(0, 3);
}

function setupSurvey() {
  const questionStage = document.querySelector("#questionStage");
  if (!questionStage) return;

  const progressBar = document.querySelector("#progressBar");
  const progressLabel = document.querySelector("#progressLabel");
  const questionCounter = document.querySelector("#questionCounter");
  const results = document.querySelector("#results");
  const state = { current: 0, scores: {}, answers: [] };

  const renderQuestion = () => {
    const question = questions[state.current];
    const progress = Math.round(((state.current + 1) / questions.length) * 100);
    progressBar.style.width = `${progress}%`;
    progressLabel.textContent = `${progress}%`;
    questionCounter.textContent = `Pregunta ${state.current + 1} de ${questions.length}`;
    questionStage.innerHTML = `
      <div class="fade-swap">
        <h3>${question.text}</h3>
        <div class="options-grid">
          ${question.options.map((option) => `<button class="option-btn" type="button" data-key="${option.key}">${option.label}</button>`).join("")}
        </div>
      </div>
    `;
  };

  const showResults = () => {
    const profileKey = getTopKey(state.scores);
    const profile = profiles[profileKey];
    const progress = loadProgress();

    progress.surveys += 1;
    progress.xp += 180;
    progress.lastProfile = profileKey;
    progress.answers = [...progress.answers, ...state.answers].slice(-30);
    progress.history = [
      ...progress.history,
      { date: new Date().toLocaleDateString("es-PE"), profile: profile.name, main: profileKey }
    ].slice(-8);
    Object.entries(state.scores).forEach(([key, value]) => {
      progress.scores[key] = (progress.scores[key] || 0) + value;
    });
    saveProgress(progress);

    progressBar.style.width = "100%";
    progressLabel.textContent = "100%";
    questionCounter.textContent = "Resultado guardado";
    questionStage.innerHTML = `
      <div class="fade-swap">
        <h3>Encuesta completada</h3>
        <p class="result-copy">Tu progreso fue guardado. Cada encuesta aumenta tu nivel de madurez.</p>
        <button class="option-btn" type="button" id="restartSurvey">Resolver otra encuesta</button>
      </div>
    `;

    results.innerHTML = `
      <div class="profile-result fade-swap">
        <div class="profile-head">
          <span class="profile-icon">${profile.icon}</span>
          <div>
            <p class="eyebrow">Perfil STEM</p>
            <h3>${profile.name}</h3>
          </div>
        </div>
        <p class="result-copy">${profile.description}</p>
        <div class="message-box"><strong>IA demo:</strong> detecte interes en ${state.answers[0]?.answer || "programacion"} y lo conecte con programacion.</div>
        <h3>Carreras recomendadas</h3>
        <div class="recommendation-grid">
          ${profile.careers.map((career) => `<span class="career-pill">${career}</span>`).join("")}
        </div>
        <div class="game-video-grid">
          <article class="game-card">
            <div class="media-thumb">GAME</div>
            <p class="eyebrow">Videojuego recomendado</p>
            <h3>${profile.game}</h3>
            <p>${profile.gameDescription}</p>
            <p><strong>Categoria STEM:</strong> ${profile.gameCategory}</p>
            <a href="https://www.google.com/search?q=${encodeURIComponent(profile.game)}" target="_blank" rel="noopener">Explorar videojuego</a>
          </article>
          <article class="video-card">
            <div class="media-thumb">YT</div>
            <p class="eyebrow">Video recomendado</p>
            <h3>${profile.video}</h3>
            <p>${profile.videoDescription}</p>
            <a href="${profile.videoUrl}" target="_blank" rel="noopener">Ver en YouTube</a>
          </article>
        </div>
        <div class="message-box">${profile.message} Revisa el dashboard para ver recomendaciones acumuladas mas precisas.</div>
      </div>
    `;
    updateCompactProgress();
  };

  questionStage.addEventListener("click", (event) => {
    const button = event.target.closest("[data-key]");
    if (!button) return;
    const question = questions[state.current];
    const answer = question.options.find((option) => option.key === button.dataset.key);
    state.scores[button.dataset.key] = (state.scores[button.dataset.key] || 0) + 1;
    state.answers.push({ question: question.text, answer: answer?.label || button.dataset.key, key: button.dataset.key });
    state.current += 1;
    state.current < questions.length ? renderQuestion() : showResults();
  });

  questionStage.addEventListener("click", (event) => {
    if (!event.target.closest("#restartSurvey")) return;
    state.current = 0;
    state.scores = {};
    state.answers = [];
    results.innerHTML = `
      <div class="placeholder-result">
        <span class="pulse-dot"></span>
        <h3>Tu perfil aparecera aqui</h3>
        <p>Completa la encuesta para desbloquear recomendaciones.</p>
      </div>
    `;
    renderQuestion();
  });

  renderQuestion();
}

function getAiReply(text) {
  const value = text.toLowerCase();
  const progress = loadProgress();
  const profile = profiles[progress.lastProfile] || profiles.programacion;
  const maturity = getMaturity(progress.surveys);
  const topKey = getTopKey(progress.scores);

  if (value.includes("madurez") || value.includes("nivel")) {
    return `${maturity.name}. Has resuelto ${progress.surveys} encuesta(s). Mientras mas resuelvas, mas precisa sera tu ruta.`;
  }
  if (value.includes("proyecto") || value.includes("hacer")) {
    const project = miniProjects.find((item) => item.area === topKey) || miniProjects[0];
    return `Proyecto recomendado: ${project.title}. Duracion: ${project.time}. Entregable: ${project.deliverable}`;
  }
  if (value.includes("carrera") || value.includes("estudio")) {
    return `Ruta concisa: ${profile.careers.slice(0, 3).join(", ")}. Tu perfil actual es ${profile.name}.`;
  }
  if (value.includes("program") || value.includes("app") || value.includes("videojuego")) {
    return "Empieza con HTML, CSS y JavaScript. Luego crea un mini juego o una app escolar visible.";
  }
  if (value.includes("robot")) {
    return "Para robotica: logica, sensores simulados y automatizacion. JavaScript puede servir para prototipos.";
  }
  if (value.includes("hola")) {
    return "Hola. Dime si te interesan apps, videojuegos, robots, datos o espacio.";
  }
  return `Respuesta breve: tu tendencia actual es ${profile.name}. Recomiendo practicar programacion con proyectos pequenos.`;
}

function setupChatbot() {
  const form = document.querySelector("#chatForm");
  if (!form) return;
  const input = document.querySelector("#chatInput");
  const messages = document.querySelector("#chatMessages");

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    const text = input.value.trim();
    if (!text) return;
    messages.insertAdjacentHTML("beforeend", `<div class="user-message">${text}</div>`);
    input.value = "";
    messages.scrollTop = messages.scrollHeight;
    setTimeout(() => {
      messages.insertAdjacentHTML("beforeend", `<div class="bot-message">${getAiReply(text)}</div>`);
      messages.scrollTop = messages.scrollHeight;
    }, 360);
  });
}

function setupProjects() {
  const grid = document.querySelector("#projectGrid");
  if (!grid) return;
  const filters = document.querySelectorAll("[data-filter]");

  const render = (filter = "todos") => {
    const progress = loadProgress();
    const topKey = getTopKey(progress.scores);
    const list = miniProjects.filter((project) => filter === "todos" || project.area === filter || (filter === "recomendados" && project.area === topKey));
    grid.innerHTML = list.map((project) => `
      <article class="project-card reveal visible">
        <span>${project.level} · ${project.time}</span>
        <h3>${project.title}</h3>
        <p>${project.description}</p>
        <div class="message-box"><strong>Entregable:</strong> ${project.deliverable}</div>
      </article>
    `).join("");
  };

  filters.forEach((button) => {
    button.addEventListener("click", () => {
      filters.forEach((item) => item.classList.remove("active"));
      button.classList.add("active");
      render(button.dataset.filter);
    });
  });

  render();
}

function setupDashboard() {
  const root = document.querySelector("#dashboardRoot");
  if (!root) return;
  const progress = loadProgress();
  const maturity = getMaturity(progress.surveys);
  const topKey = getTopKey(progress.scores);
  const profile = profiles[progress.lastProfile] || profiles[topKey] || profiles.programacion;
  const totalAnswers = Object.values(progress.scores).reduce((sum, value) => sum + value, 0) || 1;
  const recommendations = getProgrammingRecommendations(progress.answers);

  root.innerHTML = `
    <section class="dashboard-grid">
      <article class="metric-card"><span>Encuestas resueltas</span><strong>${progress.surveys}</strong></article>
      <article class="metric-card"><span>XP STEM</span><strong>${progress.xp}</strong></article>
      <article class="metric-card"><span>Nivel de madurez</span><strong>${maturity.level}</strong></article>
      <article class="metric-card"><span>Perfil dominante</span><strong>${profile.name}</strong></article>
    </section>
    <section class="two-column dashboard-block">
      <article class="glass-card">
        <p class="eyebrow">${maturity.name}</p>
        <h2>${maturity.detail}</h2>
        <div class="progress-track"><div class="progress-fill" style="width:${Math.min(100, maturity.level * 20)}%"></div></div>
        <p class="result-copy">La madurez aumenta cada vez que completas una encuesta. Con mas datos, GRAN MENTE reduce recomendaciones genericas y muestra rutas mas concretas.</p>
      </article>
      <article class="glass-card">
        <p class="eyebrow">Intereses detectados</p>
        ${Object.entries(progress.scores).map(([key, value]) => `
          <div class="interest-row">
            <span>${key}</span>
            <div class="progress-track"><div class="progress-fill" style="width:${Math.round((value / totalAnswers) * 100)}%"></div></div>
          </div>
        `).join("") || "<p class='result-copy'>Aun no hay encuestas registradas.</p>"}
      </article>
    </section>
    <section class="glass-card">
      <p class="eyebrow">Recomendaciones mas precisas</p>
      <h2>Ruta sugerida segun tus respuestas acumuladas</h2>
      <div class="learning-list">
        ${recommendations.map((item) => `
          <article>
            <span>${item.level}</span>
            <h4>${item.title}</h4>
            <p>${item.why}</p>
          </article>
        `).join("")}
      </div>
    </section>
  `;
}

function updateCompactProgress() {
  const xpValue = document.querySelector("#xpValue");
  const levelValue = document.querySelector("#levelValue");
  if (!xpValue || !levelValue) return;
  const progress = loadProgress();
  const maturity = getMaturity(progress.surveys);
  xpValue.textContent = progress.xp;
  levelValue.textContent = `${maturity.name} · Nivel ${maturity.level}`;
}

function setupCounters() {
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

function setupReveal() {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) entry.target.classList.add("visible");
    });
  }, { threshold: 0.16 });
  document.querySelectorAll(".reveal").forEach((element) => observer.observe(element));
}

function setupMenu() {
  const toggle = document.querySelector(".menu-toggle");
  const nav = document.querySelector(".main-nav");
  if (!toggle || !nav) return;
  toggle.addEventListener("click", () => {
    const isOpen = nav.classList.toggle("open");
    toggle.setAttribute("aria-expanded", String(isOpen));
  });
  nav.addEventListener("click", () => {
    nav.classList.remove("open");
    toggle.setAttribute("aria-expanded", "false");
  });
}

function setupParticles() {
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

setupSurvey();
setupChatbot();
setupProjects();
setupDashboard();
setupCounters();
setupReveal();
setupMenu();
setupParticles();
updateCompactProgress();
