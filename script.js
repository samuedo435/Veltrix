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
        authForm.querySelector('button').textContent = 'Sign In';
      } else {
        authForm.querySelector('button').textContent = 'Register';
      }
    });
  });
}

const thumbButtons = document.querySelectorAll('.gallery-thumbs .thumb');
const mainImage = document.querySelector('.main-image img');

const thumbSources = [
  'https://images.unsplash.com/photo-1595950654328-1d9f3f9ae4d8?auto=format&fit=crop&w=900&q=80',
  'https://images.unsplash.com/photo-1528701800489-20f49f92c28d?auto=format&fit=crop&w=900&q=80',
  'https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=900&q=80'
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
