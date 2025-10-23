package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class Main {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            // Load the properties file
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        String connString = properties.getProperty("AZURE_PGSQL_CONNECTIONSTRING");
        // Substitute environment variables in the connection string
        connString = substituteEnvVariables(connString);
        System.out.println("connString = " + connString);
        DefaultAzureCredential credential = new DefaultAzureCredentialBuilder().build();
        String accessToken = credential.getToken(new TokenRequestContext().addScopes("https://ossrdbms-aad.database.chinacloudapi.cn/.default")).block().getToken();
        connString = connString + "&password=" + accessToken;
        Connection connection = DriverManager.getConnection(connString);
        System.out.println("connection = " + connection.toString());
    }

    // Helper method to substitute $VARNAME or ${VARNAME} with environment variable values
    private static String substituteEnvVariables(String text) {
        if (text == null) return null;
        // First handle ${VARNAME}
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\$\\{([^}]+)\\}");
        java.util.regex.Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String var = matcher.group(1);
            String value = System.getenv(var);
            matcher.appendReplacement(sb, value != null ? java.util.regex.Matcher.quoteReplacement(value) : matcher.group(0));
        }
        matcher.appendTail(sb);
        text = sb.toString();

        // Then handle $VARNAME
        pattern = java.util.regex.Pattern.compile("\\$([A-Za-z_][A-Za-z0-9_]*)");
        matcher = pattern.matcher(text);
        sb = new StringBuffer();
        while (matcher.find()) {
            String var = matcher.group(1);
            String value = System.getenv(var);
            matcher.appendReplacement(sb, value != null ? java.util.regex.Matcher.quoteReplacement(value) : matcher.group(0));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}

