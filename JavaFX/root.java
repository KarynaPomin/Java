BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        root.setTop(topBox);
        root.setCenter(formBox);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
