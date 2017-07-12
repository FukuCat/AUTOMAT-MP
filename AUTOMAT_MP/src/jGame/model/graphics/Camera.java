package jGame.model.graphics;

import jGame.model.math.Vector3f;
import jGame.model.physics.Physics3D;

public class Camera extends Physics3D{

	public static final float DEF_LIMIT = 4000f;
	public static final float DEF_ACCEL = 300f;
	
	// Movement
	private float maxSpeed, accel;
	
	public Camera(){
		super();
		maxSpeed = DEF_LIMIT;
		accel = DEF_ACCEL;
		
	}
	
	public void reset(){
		acceleration.setValue(0.0f, 0.0f, 0.0f);
		position.setValue(0.0f, 0.0f, 0.0f);
		rotation.setValue(0.0f, 0.0f, 0.0f);
		maxSpeed = DEF_LIMIT;
		accel = DEF_ACCEL;
	}
	
	public void setPosition(float x, float y, float z){position.setValue(x, y, z);}
	
	public void resetPosition(){position.setValue(0.0f, 0.0f, 0.0f);}
	public void resetRotation(){rotation.setValue(0.0f, 0.0f, 0.0f);}
	public void incPitch(float pitch){rotation.incX(pitch);;}
	public void incYaw(float yaw){rotation.incY(yaw);;}
	public void incRoll(float roll){rotation.incZ(roll);;}
	
	public void setPitch(float pitch){rotation.setX(pitch);}
	public void setYaw(float yaw){rotation.setY(yaw);}
	public void setRoll(float roll){rotation.setZ(roll);}

	public float getPitch() {
		return rotation.getX();
	}

	public float getYaw() {
		return rotation.getY();
	}

	public float getRoll() {
		return rotation.getZ();
	}

	public void logic(long deltaTime){
		
		if(acceleration.getX() > maxSpeed)
			acceleration.setX(maxSpeed);
		if(acceleration.getX() < -maxSpeed)
			acceleration.setX(-maxSpeed);
		if(acceleration.getY() > maxSpeed)
			acceleration.setY(maxSpeed);
		if(acceleration.getY() < -maxSpeed)
			acceleration.setY(-maxSpeed);

		position.incValue(acceleration.multiply(deltaTime / 1000000000.0f));

	}
	
	public void accelDecayX(float accel, boolean status){
		if(status)
		if (acceleration.getX() < 0.0f) {
			accelerateX(accel);
			if (acceleration.getX() >= 0.0f) 
				acceleration.setX(0.0f);
		} else {
			accelerateX(-accel);
			if (acceleration.getX() <= 0.0f) 
				acceleration.setX(0.0f);
		}
	}
	
	public void accelDecayY(float accel, boolean status){
		if(status)
		if (acceleration.getY() < 0.0f) {
			accelerateY(accel);
			if (acceleration.getY() >= 0.0f) 
				acceleration.setY(0.0f);
		} else {
			accelerateY(-accel);
			if (acceleration.getY() <= 0.0f) 
				acceleration.setY(0.0f);
		}
	}
	
	public void accelDecayZ(float accel, boolean status){
		if(status)
			if (acceleration.getZ() < 0.0f) {
				accelerateZ(accel);
				if (acceleration.getZ() >= 0.0f) 
					acceleration.setZ(0.0f);
			} else {
				accelerateZ(-accel);
				if (acceleration.getZ() <= 0.0f) 
					acceleration.setZ(0.0f);
			}
	}
	
	public void accelerateX(float accel){
		acceleration.incX(accel);
	}
	
	public void accelerateY(float accel){
		acceleration.incY(accel);
	}
	
	public void accelerateZ(float accel){
		acceleration.incZ(accel);
	}

	public Vector3f getAcceleration() {
		return acceleration;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getAccel() {
		return accel;
	}

	public void setAccel(float accel) {
		this.accel = accel;
	}
}
