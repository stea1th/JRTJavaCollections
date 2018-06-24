package com.javarush.task.task36.task3609;

/* 
Рефакторинг MVC
*/

public class Solution {
    public static void main(String[] args) {
        //Fetch car record from the database
        //CarModel model = retrieveCarFromDatabase();

        //Create a view : to show car's speed on speedometer(console)
        //SpeedometerView view = new SpeedometerView();

        //CarController controller = new CarController(model, view);
        CarController controller = new CarController();
        //SpeedometerView view = new SpeedometerView(controller);
        controller.speedUp(15);
        controller.speedUp(50);
        controller.speedDown(7);


        //Update model data
        /*model.speedUp(15);
        controller.updateView();

        //Update model data
        model.speedUp(50);
        controller.updateView();

        //Update model data
        model.speedDown(7);
        controller.updateView();*/
    }


}