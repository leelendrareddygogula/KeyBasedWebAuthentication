<h2>Secure Key-Based Authentication Architecture for Web Applications</h2>
<p>In order to give web applications strong access control, this project implements a secure, REST-based authentication service. The system guarantees high-integrity user registration and login processes by utilizing contemporary cryptography standards and a modular backend design.</p>
<h3>Features</h3>
<ul>
  <li><b>Secure User Registration: </b>Provides per-user registration endpoints to establish unique credentials within the system.</li>
  <li><b>Cryptographic Security: </b>Employs AES encryption and SHA hashing to protect sensitive user data.</li>
  <li><b>Attack Mitigation: </b>Utilizes random string generation and salting techniques to defend against credential compromise and replay attacks.</li>
  <li><b>Extensible Architecture: </b>Built with a modular service design, allowing for seamless integration into enterprise-scale environments.</li>
  <li><b>Comprehensive API Validation: </b>All authentication scenarios and failure paths are validated through structured testing.</li>
</ul>
<h3>Tech Stack</h3>
<ul>
  <li><b>Language: </b>Java</li>
  <li><b>Framework: </b>Spring Boot</li>
  <li><b>Architecture: </b>REST APIs</li>
  <li><b>Security & Encryption: </b>AES, SHA, Salting</li>
  <li><b>Testing & Documentation: </b>Postman</li>
</ul>
<h3>Implementation Details</h3>
<h4>Security Workflow</h4>
<p>The authentication architecture follows a multi-layered security pipeline:</p>
<ul>
  <li><b>Input Processing: </b>User credentials are captured via RESTful endpoints.</li>
  <li><b>Hashing & Salting: </b>Passwords undergo SHA hashing combined with unique salts and random string generation to ensure that even identical passwords have unique stored signatures.</li>
  <li><b>Encryption: </b>Critical data transmission is secured using AES encryption pipelines.</li>
</ul>
<h4>System Design</h4>
<P>The project is developed using Spring Boot, focusing on a decoupled service layer that separates security logic from API controllers. This modularity ensures that the authentication logic can be updated or replaced without impacting the core application services.</P>

<h3>Security and Validation Overview</h3>
<p>To ensure high-integrity deployments, the following validation standards are implemented across all endpoints</p>
<table>
  <tr>
    <th>Feature</th>
    <th>Implementation</th>
    <th>Purpose</th>
  </tr>
  <tr>
    <td><b>Hashing</b></td>
    <td>SHA (Secure Hash Algorithm)</td>
    <td>Ensures one-way password protection.</td>
  </tr>
  <tr>
    <td><b>Encryption</b></td>
    <td>AES (Advanced Encryption Standard)</td>
    <td>Secures data transmission and sensitive fields.</td>
  </tr>
  <tr>
    <td><b>Testing</b></td>
    <td>Postman Suite</td>
    <td>Validates functional behavior across multiple failure paths.</td>
  </tr>
  <tr>
    <td><b>Design</b></td>
    <td>Modular Service Design</td>
    <td>Supports future integration into enterprise-scale systems.</td>
  </tr>
</table>
<h3>Installation & Setup</h3>
<ol>
  <li><strong>Clone the repository:</strong>
    <pre><code>git clone https://github.com/leelendrareddygogula/KeyBasedWebAuthentication.git</code></pre>
  </li>
  <li><strong>Navigate to the project folder:</strong>
    <pre><code>cd KeyBasedWebAuthentication</code></pre>
  </li>
  <li><strong>Build the application:</strong>
    <pre><code>mvn clean install</code></pre>
  </li>
  <li><strong>Run the project:</strong>
    <pre><code>mvn spring-boot:run</code></pre>
  </li>
</ol>
