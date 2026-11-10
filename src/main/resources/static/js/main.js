/**
 * ==========================================================================
 * TRAN LE ANH QUAN - PORTFOLIO INTERACTION ENGINE
 * ==========================================================================
 * Features:
 * - Interactive Particle Constellation Canvas
 * - Card Spotlight Cursor Glow
 * - Typewriter Role Rotator
 * - Multi-Tab Developer Terminal Mock & Universal Code Copy
 * - Tech Stack Category Filtering
 * - 1-Click Copy Email with Tooltip & Toast Feedback
 * - Resume / CV Modal with Print & Escape Support
 * - Scroll Progress Bar & Floating Top Button with Ring Progress
 * - Scrollspy Active Link Highlighting
 * - Dark / Light Theme Persistence
 * - Responsive Mobile Navigation Drawer
 * - Form Character Counter & Feedback
 */

document.addEventListener('DOMContentLoaded', () => {

    /* ==========================================================================
       1. THEME SWITCHER LOGIC
       ========================================================================== */
    const themeToggleBtn = document.getElementById('themeToggleBtn');
    const rootHtml = document.documentElement;

    const savedTheme = localStorage.getItem('theme');
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;

    if (savedTheme) {
        rootHtml.setAttribute('data-theme', savedTheme);
    } else if (!prefersDark) {
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

    /* ==========================================================================
       2. DYNAMIC YEAR
       ========================================================================== */
    const currentYearEl = document.getElementById('currentYear');
    if (currentYearEl) {
        currentYearEl.textContent = new Date().getFullYear();
    }

    /* ==========================================================================
       3. INTERACTIVE PARTICLE CONSTELLATION CANVAS
       ========================================================================== */
    const canvas = document.getElementById('bgCanvas');
    if (canvas && !window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
        const ctx = canvas.getContext('2d');
        let width = (canvas.width = window.innerWidth);
        let height = (canvas.height = window.innerHeight);

        const particles = [];
        const particleCount = Math.min(Math.floor((width * height) / 28000), 55);
        const maxDistance = 120;
        let mouseX = -1000;
        let mouseY = -1000;

        class Particle {
            constructor() {
                this.x = Math.random() * width;
                this.y = Math.random() * height;
                this.vx = (Math.random() - 0.5) * 0.45;
                this.vy = (Math.random() - 0.5) * 0.45;
                this.radius = Math.random() * 1.5 + 1;
            }

            update() {
                this.x += this.vx;
                this.y += this.vy;

                if (this.x < 0) this.x = width;
                else if (this.x > width) this.x = 0;

                if (this.y < 0) this.y = height;
                else if (this.y > height) this.y = 0;
            }

            draw() {
                const isLight = rootHtml.getAttribute('data-theme') === 'light';
                ctx.beginPath();
                ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
                ctx.fillStyle = isLight ? 'rgba(2, 132, 199, 0.4)' : 'rgba(56, 189, 248, 0.5)';
                ctx.fill();
            }
        }

        for (let i = 0; i < particleCount; i++) {
            particles.push(new Particle());
        }

        let animationFrameId;
        function animateParticles() {
            ctx.clearRect(0, 0, width, height);
            const isLight = rootHtml.getAttribute('data-theme') === 'light';

            for (let i = 0; i < particles.length; i++) {
                const p1 = particles[i];
                p1.update();
                p1.draw();

                // Connect nearby particles
                for (let j = i + 1; j < particles.length; j++) {
                    const p2 = particles[j];
                    const dx = p1.x - p2.x;
                    const dy = p1.y - p2.y;
                    const dist = Math.hypot(dx, dy);

                    if (dist < maxDistance) {
                        const alpha = (1 - dist / maxDistance) * (isLight ? 0.15 : 0.25);
                        ctx.beginPath();
                        ctx.moveTo(p1.x, p1.y);
                        ctx.lineTo(p2.x, p2.y);
                        ctx.strokeStyle = isLight
                            ? `rgba(2, 132, 199, ${alpha})`
                            : `rgba(56, 189, 248, ${alpha})`;
                        ctx.lineWidth = 0.8;
                        ctx.stroke();
                    }
                }

                // Connect to mouse gently if close
                const mdx = p1.x - mouseX;
                const mdy = p1.y - mouseY;
                const mDist = Math.hypot(mdx, mdy);
                if (mDist < 140) {
                    const mAlpha = (1 - mDist / 140) * (isLight ? 0.3 : 0.45);
                    ctx.beginPath();
                    ctx.moveTo(p1.x, p1.y);
                    ctx.lineTo(mouseX, mouseY);
                    ctx.strokeStyle = `rgba(99, 102, 241, ${mAlpha})`;
                    ctx.lineWidth = 1;
                    ctx.stroke();
                }
            }

            animationFrameId = requestAnimationFrame(animateParticles);
        }

        animateParticles();

        window.addEventListener('resize', () => {
            width = canvas.width = window.innerWidth;
            height = canvas.height = window.innerHeight;
        });

        window.addEventListener('mousemove', (e) => {
            mouseX = e.clientX;
            mouseY = e.clientY;
        });

        window.addEventListener('mouseleave', () => {
            mouseX = -1000;
            mouseY = -1000;
        });
    }

    /* ==========================================================================
       4. CARD SPOTLIGHT CURSOR GLOW
       ========================================================================== */
    const spotlightCards = document.querySelectorAll('.spotlight-card');
    spotlightCards.forEach((card) => {
        card.addEventListener('mousemove', (e) => {
            const rect = card.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const y = e.clientY - rect.top;
            card.style.setProperty('--mouse-x', `${x}px`);
            card.style.setProperty('--mouse-y', `${y}px`);
        });
    });

    /* ==========================================================================
       5. TYPEWRITER EFFECT IN HERO
       ========================================================================== */
    const typewriterEl = document.getElementById('typewriterText');
    if (typewriterEl) {
        const roles = [
            "Java Backend Developer",
            "Spring Boot & REST API Engineer",
            "Relational Database Specialist",
            "Software Engineering @ FPT University"
        ];
        let roleIndex = 0;
        let charIndex = roles[0].length;
        let isDeleting = true;
        let typingSpeed = 80;

        function typeLoop() {
            const currentRole = roles[roleIndex];

            if (isDeleting) {
                typewriterEl.textContent = currentRole.substring(0, charIndex - 1);
                charIndex--;
                typingSpeed = 40;
            } else {
                typewriterEl.textContent = currentRole.substring(0, charIndex + 1);
                charIndex++;
                typingSpeed = 75;
            }

            if (!isDeleting && charIndex === currentRole.length) {
                // Finished typing word, pause before erasing
                isDeleting = true;
                typingSpeed = 2200;
            } else if (isDeleting && charIndex === 0) {
                // Finished erasing, move to next role
                isDeleting = false;
                roleIndex = (roleIndex + 1) % roles.length;
                typingSpeed = 400;
            }

            setTimeout(typeLoop, typingSpeed);
        }

        // Wait initial delay then start rotating
        setTimeout(typeLoop, 2500);
    }

    /* ==========================================================================
       6. TERMINAL TABS & UNIVERSAL COPY
       ========================================================================== */
    const terminalTabs = document.querySelectorAll('.terminal-tab');
    const tabContents = {
        json: document.getElementById('contentJson'),
        java: document.getElementById('contentJava'),
        status: document.getElementById('contentStatus')
    };
    const copyCodeBtn = document.getElementById('copyCodeBtn');

    terminalTabs.forEach(tab => {
        tab.addEventListener('click', () => {
            terminalTabs.forEach(t => {
                t.classList.remove('active');
                t.setAttribute('aria-selected', 'false');
            });
            tab.classList.add('active');
            tab.setAttribute('aria-selected', 'true');

            const targetTab = tab.getAttribute('data-tab');
            Object.keys(tabContents).forEach(key => {
                if (tabContents[key]) {
                    tabContents[key].style.display = key === targetTab ? 'block' : 'none';
                    if (key === targetTab) tabContents[key].classList.add('active');
                    else tabContents[key].classList.remove('active');
                }
            });
        });
    });

    if (copyCodeBtn) {
        copyCodeBtn.addEventListener('click', async () => {
            const activeTab = document.querySelector('.terminal-tab.active');
            const targetKey = activeTab ? activeTab.getAttribute('data-tab') : 'json';
            const activeContentEl = tabContents[targetKey];
            if (!activeContentEl) return;

            const textToCopy = activeContentEl.innerText.trim();
            await copyToClipboard(textToCopy);
            showToast('✓ Snippet copied to clipboard!');
        });
    }

    /* ==========================================================================
       7. TECH STACK FILTERING
       ========================================================================== */
    const skillFilterBtns = document.querySelectorAll('.skill-filter-btn');
    const techCards = document.querySelectorAll('.tech-item-card');

    skillFilterBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            skillFilterBtns.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');

            const filterValue = btn.getAttribute('data-skill-filter');

            techCards.forEach(card => {
                const category = card.getAttribute('data-category') || '';
                if (filterValue === 'all' || category.includes(filterValue)) {
                    card.classList.remove('hidden');
                } else {
                    card.classList.add('hidden');
                }
            });
        });
    });

    /* ==========================================================================
       8. 1-CLICK EMAIL COPY
       ========================================================================== */
    const copyEmailBtn = document.getElementById('copyEmailBtn');
    if (copyEmailBtn) {
        copyEmailBtn.addEventListener('click', async () => {
            const email = copyEmailBtn.getAttribute('data-email') || 'anhquan.tranle06@gmail.com';
            await copyToClipboard(email);

            const spanText = copyEmailBtn.querySelector('span');
            if (spanText) {
                const old = spanText.textContent;
                spanText.textContent = 'Copied!';
                setTimeout(() => { spanText.textContent = old; }, 2200);
            }
            showToast('✓ Email copied: ' + email);
        });
    }

    /* ==========================================================================
       9. RESUME / CV MODAL
       ========================================================================== */
    const cvModal = document.getElementById('cvModal');
    const openCvBtn = document.getElementById('openCvBtn');
    const heroCvBtn = document.getElementById('heroCvBtn');
    const closeCvBtn = document.getElementById('closeCvBtn');
    const modalDismissBtn = document.getElementById('modalDismissBtn');

    function openModal() {
        if (!cvModal) return;
        cvModal.style.display = 'flex';
        // force reflow
        void cvModal.offsetWidth;
        cvModal.classList.add('open');
        document.body.style.overflow = 'hidden';
    }

    function closeModal() {
        if (!cvModal) return;
        cvModal.classList.remove('open');
        setTimeout(() => {
            cvModal.style.display = 'none';
            document.body.style.overflow = '';
        }, 300);
    }

    if (openCvBtn) openCvBtn.addEventListener('click', openModal);
    if (heroCvBtn) heroCvBtn.addEventListener('click', openModal);
    if (closeCvBtn) closeCvBtn.addEventListener('click', closeModal);
    if (modalDismissBtn) modalDismissBtn.addEventListener('click', closeModal);

    if (cvModal) {
        cvModal.addEventListener('click', (e) => {
            if (e.target === cvModal) closeModal();
        });
    }

    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape' && cvModal && cvModal.classList.contains('open')) {
            closeModal();
        }
    });

    /* ==========================================================================
       10. READING PROGRESS & FLOATING BACK TO TOP WITH PROGRESS RING
       ========================================================================== */
    const scrollProgressBar = document.getElementById('scrollProgressBar');
    const floatingTopBtn = document.getElementById('floatingTopBtn');
    const progressRingBar = document.getElementById('progressRingBar');
    const ringRadius = 20;
    const ringCircumference = 2 * Math.PI * ringRadius;

    if (progressRingBar) {
        progressRingBar.style.strokeDasharray = `${ringCircumference}`;
        progressRingBar.style.strokeDashoffset = `${ringCircumference}`;
    }

    function onScrollWindow() {
        const scrollTop = window.scrollY || document.documentElement.scrollTop;
        const docHeight = document.documentElement.scrollHeight - document.documentElement.clientHeight;
        const scrollPercent = docHeight > 0 ? (scrollTop / docHeight) : 0;

        // 1. Top progress bar
        if (scrollProgressBar) {
            scrollProgressBar.style.width = `${Math.min(scrollPercent * 100, 100)}%`;
        }

        // 2. Floating Top Button visibility & ring offset
        if (floatingTopBtn && progressRingBar) {
            if (scrollTop > 380) {
                floatingTopBtn.classList.add('visible');
            } else {
                floatingTopBtn.classList.remove('visible');
            }

            const offset = ringCircumference - (scrollPercent * ringCircumference);
            progressRingBar.style.strokeDashoffset = `${offset}`;
        }
    }

    window.addEventListener('scroll', onScrollWindow, { passive: true });
    onScrollWindow(); // initialize

    if (floatingTopBtn) {
        floatingTopBtn.addEventListener('click', () => {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });
    }

    /* ==========================================================================
       11. SCROLLSPY ACTIVE LINK HIGHLIGHTING
       ========================================================================== */
    const navLinks = document.querySelectorAll('.nav-link');
    const observedSections = document.querySelectorAll('section[id]');

    function updateScrollSpy() {
        const scrollPosition = (window.scrollY || document.documentElement.scrollTop) + 120;

        observedSections.forEach(section => {
            const sectionTop = section.offsetTop;
            const sectionHeight = section.offsetHeight;
            const sectionId = section.getAttribute('id');

            if (scrollPosition >= sectionTop && scrollPosition < sectionTop + sectionHeight) {
                navLinks.forEach(link => {
                    link.classList.remove('active');
                    if (link.getAttribute('href') === `#${sectionId}`) {
                        link.classList.add('active');
                    }
                });
            }
        });
    }

    window.addEventListener('scroll', updateScrollSpy, { passive: true });

    /* ==========================================================================
       12. MOBILE MENU DRAWER
       ========================================================================== */
    const mobileToggle = document.getElementById('mobileMenuToggle');
    const navWrapper = document.querySelector('.nav-wrapper');

    if (mobileToggle && navWrapper) {
        mobileToggle.addEventListener('click', () => {
            const isOpen = navWrapper.classList.toggle('open');
            mobileToggle.classList.toggle('active', isOpen);
            mobileToggle.setAttribute('aria-expanded', isOpen);
        });

        // Close on link click
        navWrapper.querySelectorAll('.nav-link').forEach(link => {
            link.addEventListener('click', () => {
                navWrapper.classList.remove('open');
                mobileToggle.classList.remove('active');
                mobileToggle.setAttribute('aria-expanded', 'false');
            });
        });
    }

    /* ==========================================================================
       13. CONTACT FORM CHARACTER COUNTER
       ========================================================================== */
    const messageTextarea = document.getElementById('message');
    const charCountEl = document.getElementById('charCount');

    if (messageTextarea && charCountEl) {
        function updateCharCount() {
            const count = messageTextarea.value.length;
            charCountEl.textContent = `${count} characters`;
        }

        messageTextarea.addEventListener('input', updateCharCount);
        updateCharCount();
    }

    /* ==========================================================================
       14. TOAST NOTIFICATION UTILITY
       ========================================================================== */
    const toast = document.getElementById('toastMsg');
    const toastText = document.getElementById('toastText');
    let toastTimeout;

    function showToast(message) {
        if (!toast) return;
        if (toastText) toastText.textContent = message;
        else toast.textContent = message;

        toast.classList.add('show');
        clearTimeout(toastTimeout);
        toastTimeout = setTimeout(() => {
            toast.classList.remove('show');
        }, 2800);
    }

    async function copyToClipboard(text) {
        try {
            await navigator.clipboard.writeText(text);
        } catch (err) {
            const textarea = document.createElement('textarea');
            textarea.value = text;
            textarea.style.position = 'fixed';
            textarea.style.opacity = '0';
            document.body.appendChild(textarea);
            textarea.select();
            document.execCommand('copy');
            document.body.removeChild(textarea);
        }
    }
});
