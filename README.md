# Fullstack_Project_Casino_Cash_Machine
The QR-Based Cash Redeem System is a secure kiosk-style application designed for casino environments that allows customers to redeem winnings using a one-time encrypted QR code. The system combines backend services, a responsive frontend, and hardware integration to simulate a real-world automated cash redemption machine.

FEATURES:

>One-time encrypted QR code redemption

>PIN + QR password authentication

>Role-based access (Admin / User / Guest / Demo)

>QR code expiration (24-hour validity)

>Automatic cleanup of expired QR codes

>Optimal cash denomination calculation

>Real-time transaction logging

>Admin monitoring dashboard

>Casino-themed frontend UI

>Hardware integration (QR scanner, vibration feedback, receipt printer)


TECH STACK

>Java

>Spring Boot

>REST APIs

>MySQL

>HTML

>CSS

>JavaScript


SYSTEM WORKFLOW:

1 User scans QR code using a physical scanner

2 System prompts for PIN + QR password

3 Backend validates QR and authentication

4 Cash denomination is calculated based on availability

5 Transaction is logged in database

6 System dispenses simulated cash and prints receipt


SECURITY FEATURES:

1 One-time-use encrypted QR codes
2 4-digit PIN authentication
3 Unique QR password verification
4 Automatic expiration after 24 hours
5 Cleanup service for old QR records(After 30 days)


ADMIN CAPABILITIES:

1Monitor redeemed and expired QR codes
2View transaction history
3Manage denomination tracking
4Automatic cleanup of old QR data
