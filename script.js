const navToggle = document.querySelector('.nav-toggle');
const mainNav = document.querySelector('.main-nav');

if (navToggle && mainNav) {
  navToggle.addEventListener('click', () => {
    mainNav.classList.toggle('nav-open');
  });
}

const tabs = document.querySelectorAll('.form-switch .tab');
const authForm = document.querySelector('.auth-form');

if (tabs.length) {
  tabs.forEach((tab, index) => {
    tab.addEventListener('click', () => {
      tabs.forEach((item) => item.classList.remove('active'));
      tab.classList.add('active');
      if (index === 0) {
        authForm.querySelector('button').textContent = 'Iniciar sesión';
      } else {
        authForm.querySelector('button').textContent = 'Registrarse';
      }
    });
  });
}

const thumbButtons = document.querySelectorAll('.gallery-thumbs .thumb');
const mainImage = document.querySelector('.main-image img');

const thumbSources = [
  'zapatos/mujer/mujerRunning1.png',
  'zapatos/mujer/mujerEntrenamiento1.png',
  'zapatos/mujer/mujerCasual1.jpg'
];

thumbButtons.forEach((thumb, index) => {
  thumb.addEventListener('click', () => {
    if (mainImage) {
      mainImage.src = thumbSources[index];
      thumbButtons.forEach((btn) => btn.classList.remove('active'));
      thumb.classList.add('active');
    }
  });
});
