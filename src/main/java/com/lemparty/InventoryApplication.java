package com.lemparty;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SSLContextHelper.setSslProperties();
		System.out.println("Hellos");
		SpringApplication.run(InventoryApplication.class, args);
	}


	protected static class SSLContextHelper {

		//		private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		private static final String DEFAULT_SSL_CERTIFICATE = "rds-ca-2019-root.pem";
		private static final String SSL_CERTIFICATE = "sslCertificate";
		private static final String KEY_STORE_TYPE = "JKS";
		private static final String KEY_STORE_PROVIDER = "SUN";
		private static final String KEY_STORE_FILE_PREFIX = "sys-connect-via-ssl-test-cacerts";
		private static final String KEY_STORE_FILE_SUFFIX = ".jks";
		private static final String DEFAULT_KEY_STORE_PASSWORD = "changeit";
		private static final String SSL_TRUST_STORE = "javax.net.ssl.trustStore";
		private static final String SSL_TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
		private static final String SSL_TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";


		private static void setSslProperties() {

			try {
				String sslCertificate = System.getProperty(SSL_CERTIFICATE);
				if (StringUtils.isEmpty(sslCertificate)) {
					sslCertificate = DEFAULT_SSL_CERTIFICATE;
				}
				System.out.println(" ssl certificate path {}" + sslCertificate);
				System.setProperty(SSL_TRUST_STORE, createKeyStoreFile(sslCertificate));
				System.setProperty(SSL_TRUST_STORE_TYPE, KEY_STORE_TYPE);
				System.setProperty(SSL_TRUST_STORE_PASSWORD, DEFAULT_KEY_STORE_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private static String createKeyStoreFile(String sslCertificate) throws Exception {
			return createKeyStoreFile(createCertificate(sslCertificate)).getPath();
		}

		private static X509Certificate createCertificate(String sslCertificate) throws Exception {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			URL url = new File(sslCertificate).toURI().toURL();
			if (url == null) {
				throw new Exception();
			}
			try (InputStream certInputStream = url.openStream()) {
				return (X509Certificate) certFactory.generateCertificate(certInputStream);
			}
		}

		private static File createKeyStoreFile(X509Certificate rootX509Certificate) throws Exception {
			File keyStoreFile = File.createTempFile(KEY_STORE_FILE_PREFIX, KEY_STORE_FILE_SUFFIX);
			try (FileOutputStream fos = new FileOutputStream(keyStoreFile.getPath())) {
				KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE, KEY_STORE_PROVIDER);
				ks.load(null);
				ks.setCertificateEntry("rootCaCertificate", rootX509Certificate);
				ks.store(fos, DEFAULT_KEY_STORE_PASSWORD.toCharArray());
			}
			return keyStoreFile;
		}
	}

}
