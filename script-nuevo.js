function setupStudentLogin() {
  const form = document.querySelector("#loginForm");
  const userNameLabel = document.querySelector("#studentNameLabel");
  const userAvatar = document.querySelector("#studentAvatar");
  const userDropdown = document.querySelector("#userDropdown");
  const userToggle = document.querySelector("#studentToggle");
  const dropdownItems = document.querySelectorAll(".user-dropdown .dropdown-item");
  const logout = document.querySelector("#logoutStudent");

  const closeDropdown = () => {
    if (userDropdown) {
      userDropdown.classList.remove("open");
      if (userToggle) userToggle.setAttribute("aria-expanded", "false");
    }
  };

  const openDropdown = () => {
    if (userDropdown) {
      userDropdown.classList.add("open");
      if (userToggle) userToggle.setAttribute("aria-expanded", "true");
    }
  };

  const toggleDropdown = (event) => {
    event.stopPropagation();
    if (!userDropdown) return;
    if (userDropdown.classList.contains("open")) {
      closeDropdown();
    } else {
      openDropdown();
    }
  };

  const showPanel = (student) => {
    if (userNameLabel) userNameLabel.textContent = student.name;
    if (userAvatar) userAvatar.textContent = student.name.slice(0, 1).toUpperCase();
    document.body.classList.add("logged-in");
    closeDropdown();
    if (form) {
      form.closest(".login-card").style.display = "none";
      window.location.hash = "inicio";
    }
  };

  const showForm = () => {
    document.body.classList.remove("logged-in");
    closeDropdown();
    if (form) {
      form.closest(".login-card").style.display = "grid";
      window.location.hash = "login";
    }
  };

  const storedStudent = loadStudent();
  if (!form) {
    if (storedStudent) {
      document.body.classList.add("logged-in");
      showPanel(storedStudent);
    }
    return;
  }

  if (storedStudent) {
    showPanel(storedStudent);
  } else {
    showForm();
  }

  form.addEventListener("submit", (event) => {
    event.preventDefault();
    const name = document.querySelector("#studentName").value.trim();
    const grade = document.querySelector("#studentGrade").value;
    const password = document.querySelector("#studentPassword").value.trim();
    const hint = form.querySelector(".login-hint");

    if (name.toLowerCase() !== "fernando" || password !== "12345") {
      hint.textContent = "Usuario o contrasena incorrectos. Usa Fernando y 12345.";
      return;
    }

    const student = { name: "Fernando", grade, loginDate: new Date().toLocaleDateString("es-PE") };
    saveStudent(student);
    ensureDemoProgress();
    hint.textContent = "Ingreso correcto.";
    showPanel(student);
  });

  userToggle?.addEventListener("click", toggleDropdown);

  dropdownItems.forEach((item) => {
    if (!item.classList.contains("logout")) {
      item.addEventListener("click", closeDropdown);
    }
  });

  document.addEventListener("click", (event) => {
    if (userToggle && userDropdown) {
      if (!userToggle.contains(event.target) && !userDropdown.contains(event.target)) {
        closeDropdown();
      }
    }
  });

  logout?.addEventListener("click", () => {
    clearStudent();
    if (form) form.reset();
    showForm();
  });
}
