package com.JDBC.project;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {
private static final int PORT =
        Integer.parseInt(System.getenv().getOrDefault("PORT", "8000"));
    private static final CollegePredictorService service = new CollegePredictorService();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        // Serve static files
        server.createContext("/", new StaticFileHandler());

        // API Endpoint
        server.createContext("/api/predict", new PredictionHandler());

        server.setExecutor(null); // creates a default executor
        System.out.println("Server started on port " + PORT);
        server.start();
    }

    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String uri = t.getRequestURI().getPath();
            if (uri.equals("/")) {
                uri = "/index.html";
            }
            File file = new File("public" + uri);
            if (!file.exists()) {
                String response = "404 (Not Found)\n";
                t.sendResponseHeaders(404, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String mimeType = "text/plain";
                if (uri.endsWith(".html"))
                    mimeType = "text/html";
                else if (uri.endsWith(".css"))
                    mimeType = "text/css";
                else if (uri.endsWith(".js"))
                    mimeType = "application/javascript";

                t.getResponseHeaders().set("Content-Type", mimeType);
                t.sendResponseHeaders(200, file.length());
                OutputStream os = t.getResponseBody();
                FileInputStream fs = new FileInputStream(file);
                final byte[] buffer = new byte[0x10000];
                int count = 0;
                while ((count = fs.read(buffer)) >= 0) {
                    os.write(buffer, 0, count);
                }
                fs.close();
                os.close();
            }
        }
    }

    static class PredictionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            if ("POST".equals(t.getRequestMethod())) {
                InputStream is = t.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                Map<String, String> params = parseFormData(body);

                int rank = Integer.parseInt(params.getOrDefault("rank", "0"));
                String gender = params.getOrDefault("gender", "MALE");
                String branch = params.getOrDefault("branch", "ALL");
                String category = params.getOrDefault("category", "OC");

                List<College> colleges = service.findColleges(rank, gender, branch, category);

                String jsonResponse = convertToJson(colleges);

                t.getResponseHeaders().set("Content-Type", "application/json");
                t.sendResponseHeaders(200, jsonResponse.length());
                OutputStream os = t.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            } else {
                t.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
        }

        private Map<String, String> parseFormData(String body) {
            Map<String, String> map = new HashMap<>();
            // Expecting JSON body not form-data for simplicity in fetch
            // Let's assume the frontend sends JSON

            // Simple JSON parser for flat structure
            body = body.trim();
            if (body.startsWith("{") && body.endsWith("}")) {
                body = body.substring(1, body.length() - 1);
                String[] pairs = body.split(",");
                for (String pair : pairs) {
                    String[] entry = pair.split(":");
                    if (entry.length == 2) {
                        String key = entry[0].trim().replaceAll("\"", "");
                        String value = entry[1].trim().replaceAll("\"", "");
                        map.put(key, value);
                    }
                }
            }
            return map;
        }

        private String convertToJson(List<College> colleges) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < colleges.size(); i++) {
                College c = colleges.get(i);
                sb.append(String.format(
                        "{\"id\":%d, \"instCode\":\"%s\", \"collegeName\":\"%s\", \"branchCode\":\"%s\", \"fees\":%.2f}",
                        c.getId(), c.getInstCode(), c.getCollegeName(), c.getBranchCode(), c.getFees()));
                if (i < colleges.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}

