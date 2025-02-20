User currentUser = users.stream().filter(p -> p.name.equals(userName)).findFirst().get();

            try {
                URL url = new URL("http://localhost:3001/ping?username=" + userName);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println(content.toString());
                in.close();
                con.disconnect();

                / ///
                String requestJson = new Gson().toJson(currentUser);

                URL url = new URL("http://localhost:3001/send");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("Authorization", UUID.randomUUID().toString());
                con.setDoOutput(true);
                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = requestJson.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                System.out.println(content.toString());
                in.close();
                con.disconnect();
            } catch (Exception httpError) {
                httpError.printStackTrace();
            }
            
