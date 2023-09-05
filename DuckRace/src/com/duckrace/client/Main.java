package com.duckrace.client;

/*
 * Gets the application going, and that's it
 */

import com.duckrace.app.DuckRaceApp;

class Main {
    public static void main(String[] args) {
        // instantiate controller and say "go"
        DuckRaceApp app = new DuckRaceApp();
        app.execute();
    }

}   // END OF CLASS