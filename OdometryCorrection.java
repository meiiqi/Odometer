/* 
 * OdometryCorrection.java
 */
package ev3Odometer;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

public class OdometryCorrection extends Thread {
	//create local variables
	private static final long CORRECTION_PERIOD = 10; 
	private Odometer odometer;
	SampleProvider csColor;
	float[] csData;
	
	// constructor
	public OdometryCorrection(Odometer odometer, SampleProvider csColor, float[] csData ) {
		this.odometer = odometer;
		this.csColor = csColor;
		this.csData = csData;
	}

	// run method (required for Thread)
	public void run() {
		long correctionStart, correctionEnd;
		int i = 0;
		
		while (true) {
			correctionStart = System.currentTimeMillis();

			/*
			//odometer correction
			csColor.fetchSample(csData,0); //store data from sensor into array	
			
			
			if (csData[0] == 13){ //13 is the color Id of black (for black lines)
				switch (i) { //each case represents a line
				//setX or setY depends on which direction (either along x-axis or y-axis) it's heading
				//assuming it starts along the y-axis, x=0.
				case 0: odometer.setY(15.24-1.5);        //1.5 is distance from wheelbase to sensor
						i++;
						Sound.beep();					//beep for better control
						try{                                 //sleep 3s to avoid random errors
							Thread.sleep(3000);
							}
							catch(InterruptedException e){
								
							}
						break;
				case 1: odometer.setY(45.72-1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(3000);
							}
							catch(InterruptedException e){
								
							}
						break;
				
				case 2: odometer.setY(76.2-1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(5500); 				//sleep 5s because turns take longer
							}
							catch(InterruptedException e){
								
							}
						break;
				
				
				case 3: odometer.setX(15.24-1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(3000);
							}
							catch(InterruptedException e){
								
							}
						break;
						
				case 4: odometer.setX(45.72-1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(3000);
							}
							catch(InterruptedException e){
								
							}
						break;
						
				case 5: odometer.setX(76.2-1.5);
				i++;
				Sound.beep();
				try{
					Thread.sleep(5500);
					}
					catch(InterruptedException e){
						
					}
				break;
			
				case 6: odometer.setY(76.2+1.5);
				i++;
				Sound.beep();
				try{
					Thread.sleep(3000);
					}
					catch(InterruptedException e){
						
					}
				break;
				
				case 7: odometer.setY(45.72+1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(3000);
							}
							catch(InterruptedException e){
								
							}
						break;
						
				case 8: odometer.setY(15.24+1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(4000);
							}
							catch(InterruptedException e){
								
							}
						break;
				
				case 9: odometer.setX(76.2+1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(3000);
							}
							catch(InterruptedException e){
								
							}
						break;
				
				case 10: odometer.setX(45.72+1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(3000);
							}
							catch(InterruptedException e){
								
							}
						break;
				case 11: odometer.setX(15.24+1.5);
						i++;
						Sound.beep();
						try{
							Thread.sleep(5500);
							}
							catch(InterruptedException e){
								
							}
						break;
						
				}
				
			} */

			// this ensure the odometry correction occurs only once every period
			correctionEnd = System.currentTimeMillis();
			if (correctionEnd - correctionStart < CORRECTION_PERIOD) {
				try {
					Thread.sleep(CORRECTION_PERIOD
							- (correctionEnd - correctionStart));
				} catch (InterruptedException e) {
					// there is nothing to be done here because it is not
					// expected that the odometry correction will be
					// interrupted by another thread
				}
			}
		}
	}
}
