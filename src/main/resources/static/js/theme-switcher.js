document.addEventListener('DOMContentLoaded', () => {
    const toggleButton = document.getElementById('theme-toggle');
    const body = document.body;
    const themeIcon = document.getElementById('theme-icon');
    const navbar = document.querySelector('.navbar');

    function applyTheme(theme) {
        body.classList.remove('light-theme', 'dark-theme');
        body.classList.add(theme);
        
        localStorage.setItem('theme', theme);

        if (navbar) {
            if (theme === 'dark-theme') {
                themeIcon.textContent = '☀️';
                navbar.classList.remove('navbar-light');
                navbar.classList.add('navbar-dark');
            } else {
                themeIcon.textContent = '🌙';
                navbar.classList.remove('navbar-dark');
                navbar.classList.add('navbar-light');
            }
        }
    }

    const savedTheme = localStorage.getItem('theme') || 'light-theme';
    applyTheme(savedTheme); 

    if (toggleButton) {
        toggleButton.addEventListener('click', () => {
            const currentTheme = body.classList.contains('dark-theme') ? 'dark-theme' : 'light-theme';
            const newTheme = currentTheme === 'dark-theme' ? 'light-theme' : 'dark-theme';
            
            applyTheme(newTheme);
        });
    }
});