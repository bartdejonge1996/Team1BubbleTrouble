package gui;
import logic.Button;
import logic.HighScoresParser;
import logic.Logger;
import logic.Score;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class that represents the state of the game when the game is over.
 * @author Menno
 *
 */
public class GameOverState extends BasicGameState {

	private Button playButton;
	private Button menuButton;
	private Button saveButton;
	private Button exitButton;
	
	private MainGame mainGame;
	private TextField textField;
	private Image tfBackgroundN;
	private Image tfBackgroundA;
	private Image health0Image;
	private Image health1Image;
	private Image health2Image;
	private Image health3Image;
	private Image health4Image;
	private Image health5Image;
	private String inputMessage;

	private boolean highScoreEntered;
	
	private static final int TEXT_X = 164;
	private static final int TEXT_1_Y = 238;
	private static final int TEXT_2_Y = 288;
	private static final int TEXT_3_Y = 338;
	private static final int TEXT_4_Y = 538;

	private static final int BUTTON_X = 150;
	private static final int SAVE_BUTTON_Y = 525;
	private static final int PLAY_BUTTON_Y = 575;
	private static final int MENU_BUTTON_Y = 625;
	private static final int EXIT_BUTTON_Y = 675;
	
	private static final int HIGHSCORES_X = 1000;
	
	private static final int BUTTON_WIDTH = 1000;
	private static final int BUTTON_HEIGHT = 50;
	
	private static final int MOUSE_OVER_RECT_X = 500;
	
	private static final int TEXT_FIELD_X = 164;
	private static final int TEXT_FIELD_Y = 438;
	private static final int TEXT_FIELD_WIDTH = 700;
	private static final int TEXT_FIELD_HEIGHT = 60;
	private static final int TF_BACKGROUND_DEVIATION = 27;
	
	private static final int HEALTH_IMAGE_THREE = 3;
	private static final int HEALTH_IMAGE_FOUR = 4;
	private static final int HEALTH_IMAGE_FIVE = 5;
	
	private static final int LOGO_X = 160;
	private static final int LOGO_Y = 110;
	private static final int SEPARATOR_X = 164;
	private static final int SEPARATOR_Y = 190;
	private static final int BOTTOM_TEXT_OFFSET_X = 250;
	private static final int BOTTOM_TEXT_OFFSET_Y = 75;
	private static final int MAX_NAME_LENGTH = 34;
	
	private int displayLives;
	
	/**
	 * Constructor.
	 * @param mainGame the maingame in which this state will be used.
	 */
	public GameOverState(MainGame mainGame) {
		this.mainGame = mainGame;
	}
	
	/**
	 * Init method - load resources here.
	 * @param container The container this state should be initialized in.
	 * @param arg1 the StateBasedGame this state is in.
	 * @throws SlickException idk when
	 */
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		initButtons();
		initHealthImages();
		initTextFieldBackgroundImgs();
	}

	private void initTextFieldBackgroundImgs() throws SlickException {
		tfBackgroundN = new Image("resources/images_UI/textfield_Norm.png");
		tfBackgroundA = new Image("resources/images_UI/textfield_Add.png");
	}

	private void initButtons() throws SlickException {
		saveButton = new Button(BUTTON_X, SAVE_BUTTON_Y,
				BUTTON_WIDTH, BUTTON_HEIGHT,
				new Image("resources/images_UI/Menu_Button_SaveHighscore_Norm.png"),
				new Image("resources/images_UI/Menu_Button_SaveHighscore_Add.png"),
				new Image("resources/images_UI/Menu_Button_SaveHighscore2_Norm.png"),
				new Image("resources/images_UI/Menu_Button_SaveHighscore2_Add.png"));
		playButton = new Button(BUTTON_X, PLAY_BUTTON_Y,
				BUTTON_WIDTH, BUTTON_HEIGHT,
				new Image("resources/images_UI/Menu_Button_PlayAgain_Norm.png"),
				new Image("resources/images_UI/Menu_Button_PlayAgain_Add.png"),
				new Image("resources/images_UI/Menu_Button_PlayAgain2_Norm.png"),
				new Image("resources/images_UI/Menu_Button_PlayAgain2_Add.png"));
		menuButton = new Button(BUTTON_X, MENU_BUTTON_Y,
				BUTTON_WIDTH, BUTTON_HEIGHT,
				new Image("resources/images_UI/Menu_Button_MainMenu_Norm.png"),
				new Image("resources/images_UI/Menu_Button_MainMenu_Add.png"),
				new Image("resources/images_UI/Menu_Button_MainMenu2_Norm.png"),
				new Image("resources/images_UI/Menu_Button_MainMenu2_Add.png"));
		exitButton = new Button(BUTTON_X, EXIT_BUTTON_Y,
				BUTTON_WIDTH, BUTTON_HEIGHT,
				new Image("resources/images_UI/Menu_Button_Quit_Norm.png"),
				new Image("resources/images_UI/Menu_Button_Quit_Add.png"),
				new Image("resources/images_UI/Menu_Button_Quit2_Norm.png"),
				new Image("resources/images_UI/Menu_Button_Quit2_Add.png"));
	}

	private void initHealthImages() throws SlickException {
		health0Image = new Image("resources/Terminal/Terminal_Lights_0.png");
		health1Image = new Image("resources/Terminal/Terminal_Lights_1.png");
		health2Image = new Image("resources/Terminal/Terminal_Lights_2.png");
		health3Image = new Image("resources/Terminal/Terminal_Lights_3.png");
		health4Image = new Image("resources/Terminal/Terminal_Lights_4.png");
		health5Image = new Image("resources/Terminal/Terminal_Lights_5.png");
	}
	
	
	@Override
	public void enter(GameContainer container, StateBasedGame sbg) {
		mainGame.getLogger().log("Entering GameOverState", Logger.PriorityLevels.LOW, "States");
		RND.setOpacity(0.0f);
		mainGame.stopSwitchState();
		textField = new TextField(container, RND.getFont_Normal(), TEXT_FIELD_X, TEXT_FIELD_Y,
				TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
		textField.setBackgroundColor(null);
		textField.setBorderColor(null);
		textField.setFocus(true);
		displayLives = mainGame.getLifeCount();
		mainGame.setLifeCount(MainGame.getLives());
		inputMessage = null;
		highScoreEntered = false;
	}
	
	/**
	 * Exit function for state. Fades out and everything.
	 * @param container the GameContainer we are running in
	 * @param sbg the gamestate cont.
	 * @param delta the deltatime in ms
	 */
	public void exit(GameContainer container, StateBasedGame sbg, int delta) {
		if (mainGame.getShouldSwitchState()) {
			if (RND.getOpacity() > 0.0f) {
				int fadeTimer = mainGame.getOpacityFadeTimer();
				if (mainGame.getSwitchState() == -1) {
					fadeTimer = 2 * 2 * 2 * fadeTimer;
				}
				RND.setOpacity(RND.getOpacity() - ((float) delta) / fadeTimer);
			} else {
				mainGame.getLogger().log("Exiting GameOverState", 
						Logger.PriorityLevels.LOW, "States");
				if (mainGame.getSwitchState() == -1) {
					System.exit(0);
				} else {
					mainGame.switchColor();
					sbg.enterState(mainGame.getSwitchState());
				}
			}	
		}
	}
	
	/**
	 * update method - called every frame.
	 * @param container The container this state is in.
	 * @param sbg the StateBasedGame this state is in
	 * @param delta the time in ms since the last frame
	 * @throws SlickException sometimes.
	 */
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		if (RND.getOpacity() < 1.0f && !mainGame.getShouldSwitchState()) {
			RND.setOpacity(RND.getOpacity() + ((float) delta) / mainGame.getOpacityFadeTimer());
		}
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && !mainGame.getShouldSwitchState()) {
			processButtons(input);
		}
		handleTextField(input);
		exit(container, sbg, delta);
	}
	
	private void processButtons(Input input) {
		if (playButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
			// Start over
			mainGame.resetLifeCount();
			mainGame.resetLevelCount();
			mainGame.setScore(0);
			mainGame.setSwitchState(mainGame.getGameState());
			mainGame.getLogger().log("play button clicked", 
					Logger.PriorityLevels.MEDIUM, "user-input");
		} 
		else if (saveButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
			// Save score
			saveScore();
			mainGame.getLogger().log("save button clicked", 
					Logger.PriorityLevels.MEDIUM, "user-input");
		}
		else if (menuButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
			// Go to startState
			mainGame.setScore(0);
			mainGame.setLevelCounter(0);
			mainGame.setSwitchState(mainGame.getStartState());
			mainGame.getLogger().log("Menu Button clicked", 
					Logger.PriorityLevels.MEDIUM, "user-input");
		}
		else if (exitButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
			mainGame.setSwitchState(-1);
			mainGame.getLogger().log("exit button clicked", 
					Logger.PriorityLevels.MEDIUM, "user-input");
		}
	}
	
	private void handleTextField(Input input) {
		if (textField.hasFocus() && input.isKeyPressed(Input.KEY_ENTER) && (inputMessage == null 
				|| inputMessage.equals("Maximum length is 34 characters")) 
				&& !highScoreEntered) {
			if (textField.getText().length() < MAX_NAME_LENGTH) {
                highScoreEntered = true;
                saveScore(); }
			else {
				inputMessage = "Maximum length is 34 characters";
            }
		}
	}

	/**
	 * Render method - draw things to screen.
	 * @param container The container this state is in
	 * @param arg1 the statebasedgame this state is in.
	 * @param graphics the Graphics used in this gameoverstate.
	 * @throws SlickException idk when
	 */
	public void render(GameContainer container, StateBasedGame arg1, Graphics graphics)
			throws SlickException {
		graphics.drawImage(mainGame.getBackgroundImage(), 0, 0);
		renderEndText(container, graphics);

		RND.drawColor(graphics, tfBackgroundN, tfBackgroundA,
				textField.getX() - TF_BACKGROUND_DEVIATION,
				textField.getY() - TF_BACKGROUND_DEVIATION,
				mainGame.getColor());
		RND.text(graphics, textField.getX(), textField.getY(),
				textField.getText(), mainGame.getColor());
		if (inputMessage != null) {
			RND.text(graphics, TEXT_X, TEXT_4_Y, inputMessage);
		}
		renderButtons(container, graphics);
		mainGame.drawWaterMark();
		RND.drawColor(graphics, mainGame.getGameLogoN(), mainGame.getGameLogoA(),
				LOGO_X, LOGO_Y, mainGame.getColor());
		RND.text(graphics, SEPARATOR_X, SEPARATOR_Y, "========================");
		mainGame.getHighscores().sort();
		String highScoresString = mainGame.getHighscores().toString();
		RND.text(graphics, HIGHSCORES_X, SEPARATOR_Y, highScoresString);
		graphics.drawImage(mainGame.getForeGroundImage(), 0, 0);
		graphics.drawImage(mainGame.getTerminalImage(), 0, 0);
		renderLives(graphics);
	}

	private void renderEndText(GameContainer container, Graphics graphics) {
		RND.text(graphics, container.getWidth() / 2 - BOTTOM_TEXT_OFFSET_X,
				container.getHeight() - BOTTOM_TEXT_OFFSET_Y,
				"Waiting for user input...");
		if (displayLives < 1) {
			RND.text(graphics, TEXT_X, TEXT_1_Y, "# Game Over");
		} else {
			RND.text(graphics, TEXT_X, TEXT_1_Y, "# You won! You are the champion!");
		}

		RND.text(graphics, TEXT_X, TEXT_2_Y, "# Your score was: " + mainGame.getScore());
		RND.text(graphics, TEXT_X, TEXT_3_Y, "# Please enter your name below");
	}

	/**
	 * Renders the life-lights in the bottom left corner.
	 * @param graphics the graphics to render to
	 */
	private void renderLives(Graphics graphics) {
		switch (displayLives) {
		case(0) :
			graphics.drawImage(health0Image, 0, 0);
		break;
		case(1) :
			graphics.drawImage(health1Image, 0, 0);
		break;
		case(2) :
			graphics.drawImage(health2Image, 0, 0);
		break;
		case(HEALTH_IMAGE_THREE) :
			graphics.drawImage(health3Image, 0, 0);
		break;
		case(HEALTH_IMAGE_FOUR) :
			graphics.drawImage(health4Image, 0, 0);
		break;
		case(HEALTH_IMAGE_FIVE) :
			graphics.drawImage(health5Image, 0, 0);
		break;
		default:
			try {
				throw new SlickException("Life count was not in the correct range");
			} catch (SlickException e) {
				e.printStackTrace();
			}
	}
	}
	
	/**
	 * Method renders buttons in GameOverState to screen.
	 * @param container appgamecontainer used
	 * @param graphics graphics context used
	 */
	private void renderButtons(GameContainer container, Graphics graphics) {
		Input input = container.getInput();
		if (playButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
			RND.drawColor(graphics, playButton.getImageMouseOverN(), 
					playButton.getImageMouseOverA(), playButton.getX(), playButton.getY(), 
					mainGame.getColor());
		} else {
			RND.drawColor(graphics, playButton.getImageN(), playButton.getImageA(),
					playButton.getX(), playButton.getY(), mainGame.getColor());
		}
		if (menuButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
			RND.drawColor(graphics, menuButton.getImageMouseOverN(), 
					menuButton.getImageMouseOverA(), menuButton.getX(), menuButton.getY(), 
					mainGame.getColor());
		} else {
			RND.drawColor(graphics, menuButton.getImageN(), menuButton.getImageA(),
					menuButton.getX(), menuButton.getY(), mainGame.getColor());
		}
		drawButtonMouseOvers(input, graphics);
	}
	
	private void drawButtonMouseOvers(Input input, Graphics graphics) {
		if (inputMessage == null) {
			if (saveButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
				RND.drawColor(graphics, saveButton.getImageMouseOverN(), 
						saveButton.getImageMouseOverA(), saveButton.getX(), saveButton.getY(), 
						mainGame.getColor());
			} else {
				RND.drawColor(graphics, saveButton.getImageN(), saveButton.getImageA(),
						saveButton.getX(), saveButton.getY(), mainGame.getColor());
			} }
		if (exitButton.getRectangle().contains(MOUSE_OVER_RECT_X, input.getMouseY())) {
			RND.drawColor(graphics, exitButton.getImageMouseOverN(), 
					exitButton.getImageMouseOverA(), exitButton.getX(), exitButton.getY(), 
					mainGame.getColor());
		} else {
			RND.drawColor(graphics, exitButton.getImageN(), exitButton.getImageA(),
					exitButton.getX(), exitButton.getY(), mainGame.getColor());
		}
	}

	/**
	 * Saves score for this player.
	 */
	private void saveScore() {
		Score score = new Score(mainGame.getScore(), textField.getText());
		mainGame.getHighscores().add(score);
		mainGame.getHighscores().sort();
		HighScoresParser.writeHighScores(mainGame.getHighscoresFile(), mainGame.getHighscores());
		inputMessage = "# " + textField.getText() + ", your score of " + mainGame.getScore();
		inputMessage += " points is saved!";
		mainGame.getLogger().log("Score saved", Logger.PriorityLevels.MEDIUM, "highscores");
	}
	
	
	/**
	 * returns id of the state.
	 */
	@Override
	public int getID() {
		return 2;
	}
}
