/**
 * Portfolio Interactive Scripts
 * - Dark/Light Theme Switcher with LocalStorage
 * - API Response JSON Copy functionality
 * - Mobile Navigation Toggle
 */

document.addEventListener('DOMContentLoaded', () => {
    // 1. Theme Switcher Logic
    const themeToggleBtn = document.getElementById('themeToggleBtn');
    const rootHtml = document.documentElement;
    
    // Check saved preference or system preference
    const savedTheme = localStorage.getItem('theme');
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    
    if (savedTheme) {
        rootHtml.setAttribute('data-theme', savedTheme);
    } else if (!prefersDark) {
        // If system prefers light and no custom preference saved
        rootHtml.setAttribute('data-theme', 'light');
    } else {
        rootHtml.setAttribute('data-theme', 'dark');
    }

    if (themeToggleBtn) {
        themeToggleBtn.addEventListener('click', () => {
            const currentTheme = rootHtml.getAttribute('data-theme') || 'dark';
            const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
            rootHtml.setAttribute('data-theme', newTheme);
            localStorage.setItem('theme', newTheme);
        });
    }

    // 2. Mobile Nav Toggle
    const mobileToggle = document.getElementById('mobileMenuToggle');
    const navMenu = document.getElementById('navMenu');

    if (mobileToggle && navMenu) {
        mobileToggle.addEventListener('click', () => {
            navMenu.classList.toggle('open');
            const isExpanded = navMenu.classList.contains('open');
            mobileToggle.setAttribute('aria-expanded', isExpanded);
        });

        // Close menu when clicking link
        navMenu.querySelectorAll('.nav-link').forEach(link => {
            link.addEventListener('click', () => {
                navMenu.classList.remove('open');
                mobileToggle.setAttribute('aria-expanded', 'false');
            });
        });
    }

    // 3. Copy JSON in API Terminal Mock
    const copyJsonBtn = document.getElementById('copyJsonBtn');
    const jsonOutput = document.getElementById('jsonOutput');
    const toast = document.getElementById('toastMsg');

    if (copyJsonBtn && jsonOutput) {
        copyJsonBtn.addEventListener('click', async () => {
            const codeText = jsonOutput.innerText;
            try {
                await navigator.clipboard.writeText(codeText);
                showToast('✓ JSON copied to clipboard!');
            } catch (err) {
                // Fallback for older browsers
                const textarea = document.createElement('textarea');
                textarea.value = codeText;
                document.body.appendChild(textarea);
                textarea.select();
                document.execCommand('copy');
                document.body.removeChild(textarea);
                showToast('✓ JSON copied to clipboard!');
            }
        });
    }

    function showToast(message) {
        if (!toast) return;
        toast.textContent = message;
        toast.classList.add('show');
        setTimeout(() => {
            toast.classList.remove('show');
        }, 2500);
    }
});
