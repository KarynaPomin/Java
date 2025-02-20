private final Alert alertTimeOver = new Alert(Alert.AlertType.CONFIRMATION);

    private long ms = 5000; // 10 * 60000
    private void restarteTimer(){
        ms = 5000;
        timer();
    }

    private Label timer(){
        Label timerLabel = new Label();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(ms > 0){
                    Platform.runLater(() -> timerLabel.setText(formatTime(ms)));
                    Platform.runLater(() -> System.out.println(formatTime(ms)));
                    ms -= 1000;
                }
                if(ms == 0){
                    Platform.runLater(() -> {
                        timerLabel.setText("--:--");

                        alertTimeOver.setTitle("Twoja sesja wygaśnie");
                        Optional<ButtonType> result = alertTimeOver.showAndWait();
                        if (result.get() == ButtonType.OK){
                            timer.cancel();
                            restarteTimer();
                            return;
                        }
                        if (result.get() == ButtonType.CANCEL)
                        {
                            Memory.getInstance().logOut();

                            primaryStage.setScene(loginScene());
                            primaryStage.show();
                        }
                    });

                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,1000);

        return timerLabel;
    }

    private String formatTime(long ms) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(ms);

        ms -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(ms);

        return minutes + ":" + seconds;
    };
