CureKit - WhiteSource Cure Encoding Package
==========================

[![Language](https://img.shields.io/badge/language-Java-pink)](https://travis-ci.org/OWASP/owasp-java-encoder) [![Build Status](https://travis-ci.org/OWASP/owasp-java-encoder.svg?branch=main)](https://travis-ci.org/OWASP/owasp-java-encoder) [![License](https://img.shields.io/badge/license-Apache%202.0-blue)](https://www.apache.org/licenses/LICENSE-2.0.html)

WhiteSource Cure is an innovative solution that automatically generates remediation suggestions for vulnerabilities identified by detection tools in proprietary code.
The remediation suggestions are presented on the vulnerable code itself and can be used as-is in your IDE.

CureKit contains encoders and other utilities, critical to the Self-Healing process suggested by WhiteSource Cure.
The sanitization methods offered in CureKit offer solutions for security vulnerabilities in your code, such as 
Cross-Site Scripting, Path Traversal, Os Command Injection and more.

Contextual Output Encoding is a computer programming technique necessary to stop
Cross-Site Scripting. This project is a Java 1.5+ simple-to-use drop-in high-performance
encoder class with little baggage.

Start using the CureKit Sanitizers
-----------------------------------
You can download a JAR from [Maven Central](https://search.maven.org/#search|ga|1|g%3A%22org.owasp.encoder%22%20a%3A%22encoder%22).

The jar is also available in Maven:

```xml
<dependency>
    <groupId>io.whitesource.cure</groupId>
    <artifactId>curekit</artifactId>
    <version>1.0.0</version>
</dependency>
```

Quick Overview
--------------
The Curekit Java library is intended for quick contextual encoding with very little
overhead, either in performance or usage. To get started, simply add the encoder-1.0.0.jar,
import io.whitesource.cure.Encoder and start using.

Example usage:

```java
    PrintWriter out = ....;
    out.println("<textarea>"+Encode.forHtmlXss(userData)+"</textarea>");
```

