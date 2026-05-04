<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NepalHikeHub - Sign in</title>
    <link rel="stylesheet" href="signin.css">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

    <!-- Header Section (Matching home.html & sign up.html) -->
    <header>
        <div class="container top-header">
            <div class="logo">Nepal Hike Hub</div>
            <div class="header-right">
                <button class="btn-outline">Plan Your Trip</button>
                <span class="contact-email">info@nepalhikehub.com</span>
                <span class="contact-person">Talk to Men Raj Gurung</span>
                <div class="auth-links">
                    <a href="signup.html">Sign up</a> / <a href="signin.html" class="active-link">Sign in</a>
                </div>
            </div>
        </div>
        <nav>
            <div class="container nav-flex">
                <a href="home.html" class="nav-item">Home</a>
                <a href="#" class="nav-item">Trekking in Nepal</a>
                <a href="#" class="nav-item">Peak Climbing</a>
                <a href="#" class="nav-item">Booking</a>
                <div class="nav-spacer"></div>
                <a href="#" class="nav-item">Blog</a>
                <a href="#" class="nav-item">About US</a>
                <a href="#" class="nav-item">Contact US</a>
            </div>
        </nav>
    </header>

    <!-- Hero Placeholder Image (Same as Home/Sign Up style) -->
    <div class="hero-image-area">
        <div class="container">
            <div class="image-placeholder">
                <i class="fas fa-image fa-4x"></i>
            </div>
        </div>
    </div>

    <!-- Breadcrumb Bar -->
    <div class="breadcrumb-bar">
        <div class="container">
            <p class="breadcrumb-text">Home > Sign in</p>
        </div>
    </div>

    <!-- Sign In Title -->
    <div class="container">
        <h1 class="page-title">Sign in</h1>
    </div>

    <!-- Main Sign In Form Section -->
    <section class="form-section">
        <div class="container">
            <div class="form-wrapper">
                <form action="#">
                    <div class="form-group">
                        <label for="email">Your Email</label>
                        <input type="email" id="email" placeholder="Your Email" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" placeholder="Password" required>
                    </div>

                    <div class="forgot-link">
                        <a href="#">Forgot Password?</a>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-login">Login</button>
                    </div>

                    <p class="new-customer">New Customer? <a href="sign up.html">Create Account</a></p>
                </form>
            </div>
        </div>
    </section>

    <!-- Footer Section (Consistent with home.html) -->
    <footer>
        <div class="container footer-grid">
            <div class="footer-about">
                <h3>NepalHikeHub</h3>
                <p>Explore breathtaking trails, book your next adventure, and experience the beauty of nature with us. Your journey begins here—trusted guides, seamless booking, and unforgettable memories await in every step you take with our trekking community.</p>
            </div>
            <div class="footer-social">
                <h3>FIND US ON</h3>
                <a href="#"><i class="fab fa-facebook-f"></i> Facebook</a>
                <a href="#"><i class="fab fa-instagram"></i> Instagram</a>
                <a href="#"><i class="fab fa-twitter"></i> Twitter</a>
                <a href="#"><i class="fab fa-youtube"></i> Youtube</a>
            </div>
            <div class="footer-links">
                <h3>Useful Links</h3>
                <ul>
                    <li><a href="#">Travel Info</a></li>
                    <li><a href="#">Booking</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Contact Us</a></li>
                </ul>
            </div>
            <div class="footer-contact">
                <h3>Head office</h3>
                <p><strong>Address:</strong> Pokhara, Nepal</p>
                <p><strong>Phone:</strong> +977 9802543291</p>
                <p><strong>Email:</strong> info@nepalhikehub.com</p>
            </div>
        </div>
    </footer>

</body>
</html>