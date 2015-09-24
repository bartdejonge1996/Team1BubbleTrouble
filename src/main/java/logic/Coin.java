package logic;

/**
 * Created by alexandergeenen on 09/09/15.
 */
public class Coin {
    private static final float COIN_WIDTH = 20;
    private static final float COIN_HEIGHT = 20;
    private static final float COIN_SPEED = 200f;
    private static final int COIN_CHEAP = 200;
    private static final int COIN_EXPENSIVE = 400;

    private int points;
    private float x, y, width, height, xId, yId;
    private boolean largeAmount;

    /**
     * Create a coin.
     * @param x x coord
     * @param y y coord
     * @param largeAmount	- if the coin has a large amount of points
     */
    public Coin(float x, float y, boolean largeAmount) {
        this.x = x;
        this.y = y;
        this.xId = x;
        this.yId = y;
        this.width = COIN_WIDTH;
        this.height = COIN_HEIGHT;
        
        this.largeAmount = largeAmount;
        if (largeAmount) {
        	this.points = COIN_EXPENSIVE;
        } else {
        	this.points = COIN_CHEAP;
        }
    }

    /**
    * Create a string out of a Coin.
    * @return Coin as a string
    */
   @Override
   public String toString() {
   	String res = "COIN " + this.xId + " " + this.yId + " " + this.largeAmount + " ";
   	return res;
   }
    
    /**
     * Update Powerups graphical thingy.
     * @param floor - floor of the game.
     * @param containerHeight - height of gamecontainer.
     * @param deltaFloat Delta
     */
    public void update(MyRectangle floor, float deltaFloat, float containerHeight) {
        if ((this.y + COIN_HEIGHT) < containerHeight - floor.getHeight()) {
            this.y += COIN_SPEED * deltaFloat;
        } else {
            this.y = containerHeight - floor.getHeight() - COIN_HEIGHT;
        }
    }

    /**
     * @return Points Value of the Coin
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return x coord
     */
    public float getX() {
        return this.x;
    }

    /**
     * 
     * @return .
     */
    public float getxId() {
		return xId;
	}

    /**
     * 
     * @param xId .
     */
	public void setxId(float xId) {
		this.xId = xId;
	}

	/**
	 * 
	 * @return .
	 */
	public float getyId() {
		return yId;
	}

	/**
	 * .
	 * @param yId .
	 */
	public void setyId(float yId) {
		this.yId = yId;
	}

	/**
     * @return y coord
     */
    public float getY() {
        return this.y;
    }
    
    /**
     * @return x coord
     */
    public float getCenterX() {
        return this.x + this.width / 2;
    }

    /**
     * @return y coord
     */
    public float getCenterY() {
        return this.y + this.height / 2;
    }

    /**
     * Return a bounding box rectangle of the coin.
     * @return a bounding box rectangle
     */
    public MyRectangle getRectangle() {
        return new MyRectangle(x, y, width, height);
    }
}
