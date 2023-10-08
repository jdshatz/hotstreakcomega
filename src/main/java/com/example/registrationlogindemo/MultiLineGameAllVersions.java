package com.example.registrationlogindemo;

//EDIT THIS

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.HashMap;


public class MultiLineGameAllVersions extends Pane {
    private final MultiLinePlayer player;
    private int numberOfLines = 2; //Take as parameter when testing/playing a game
    private int cardNumber = 0;
    private Line[] lines;
    private int linesStillGoing;
    protected String[] guesses;
    private int streaksOfFour = 0;
    private int credits;
    private int flushes = 0;
    private HashMap<Integer, Integer> payouts;
    private boolean tiesWin;
    private final HotStreakGui gui;
    private boolean waiting = true;
    private boolean guessIsMade = false;
    private boolean guessIsHigher = false;
    private String gameString;
    private final Payouts setUpPayouts;

    private HashMap<Integer, Integer> columnPairs;
    private HashMap<Integer, Integer> exactPairs;
    private HashMap<Integer, Integer> finalCards;
    private HashMap<Integer, Integer> twoFlush;

    private final ColumnPairPayouts columnPairPayouts;
    private final ExactPairPayouts exactPairPayouts;
    private final FinalCard2APayouts finalCard2APayouts;
    private final TwoFlushPayouts twoFlushPayouts;

    private boolean canReplace = false;
    private boolean activatePokerBonus = false;
    private boolean onlyStartingCardReplace = false;
    private boolean replaceCall = false;

    public boolean isEndsWithAce() {
        return endsWithAce;
    }

    public void setEndsWithAce(boolean endsWithAce) {
        this.endsWithAce = endsWithAce;
    }

    public boolean isExactPair() {
        return exactPair;
    }

    public void setExactPair(boolean exactPair) {
        this.exactPair = exactPair;
    }

    public boolean isColumnPair() {
        return columnPair;
    }

    public void setColumnPair(boolean columnPair) {
        this.columnPair = columnPair;
    }

    private boolean endsWithAce = false;
    private boolean exactPair = false;
    private boolean columnPair = false;

    private Stopwatch stopwatch;

    public MultiLineGameAllVersions(MultiLinePlayer player) throws IOException, URISyntaxException {
        this.player = player;
        this.credits = 1;
        this.tiesWin = false;
        this.gameString = "Basic Ties Lose";
        this.setUpPayouts = new Payouts();
        this.columnPairPayouts = new ColumnPairPayouts();
        this.exactPairPayouts = new ExactPairPayouts();
        this.finalCard2APayouts = new FinalCard2APayouts();
        this.twoFlushPayouts = new TwoFlushPayouts();
        this.payouts = setPayouts(2);
        this.columnPairs = setColumnPairs(2);
        this.exactPairs = setExactPairs(2);
        this.finalCards = setFinalCards(2);
        this.twoFlush = setTwoFlush(2);
        this.gui = new HotStreakGui();
        this.getChildren().add(gui);
        gui.tiesLoseBasic.setDisable(true);
    }

    public String getGameString() {
        return gameString;
    }

    public void setGameString(String gameString) {
        this.gameString = gameString;
    }

    public void setTiesWin(boolean tiesWin) {
        this.tiesWin = tiesWin;
    }

    private HashMap<Integer, Integer> setPayouts(int lines) {
        return setUpPayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setColumnPairs(int lines) {
        return columnPairPayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setExactPairs(int lines) {
        return exactPairPayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setFinalCards(int lines) {
        return finalCard2APayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setTwoFlush (int lines) {
        return twoFlushPayouts.setNewPayouts(getGameString(), lines);
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public int getNumberOfLinesStillGoing() {
        return linesStillGoing;
    }

    public void setNumberOfLinesStillGoing(int numberOfLines) {
        this.linesStillGoing = numberOfLines;
    }


    public void setUpLines() {
        this.lines = new Line[numberOfLines];
        this.guesses = new String[numberOfLines];
        for (int index = 0; index < this.numberOfLines; index++) {
            lines[index] = new Line(); //Instantiate lines in array
            lines[index].setLineNumber(index);
        }
        this.payouts = setPayouts(numberOfLines);
        this.columnPairs = setColumnPairs(numberOfLines);
        this.exactPairs = setExactPairs(numberOfLines);
        this.finalCards = setFinalCards(numberOfLines);
        this.twoFlush = setTwoFlush(numberOfLines);
    }

    public int getStreaksOfFour() {
        return streaksOfFour;
    }

    public int getFlushes() {
        return flushes;
    }

    public void setFlushes(int flushes) {this.flushes = flushes;}

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setGuessIsMade(boolean guessIsMade) {
        this.guessIsMade = guessIsMade;
        System.out.println("Guess is made changed");
    }

    public void setGuessIsHigher(boolean guessIsHigher) {
        this.guessIsHigher = guessIsHigher;
    }

    public boolean isCanReplace() {
        return canReplace;
    }

    public void setCanReplace(boolean canReplace) {
        this.canReplace = canReplace;
        System.out.println("can replace? " + isCanReplace());
    }

    public boolean isActivatePokerBonus() {
        return activatePokerBonus;
    }

    public void setActivatePokerBonus(boolean activatePokerBonus) {
        this.activatePokerBonus = activatePokerBonus;
    }

    public void setOnlyStartingCardReplace(boolean canReplace) {
        this.onlyStartingCardReplace = canReplace;
    }

    public void setReplaceCall(boolean replace) {
        this.replaceCall = replace;
    }

    public HashMap<Integer, Integer> getPayouts() {
        return payouts;
    }

    public HashMap<Integer, Integer> getColumnPairs() {
        return columnPairs;
    }

    public HashMap<Integer, Integer> getExactPairs() {
        return exactPairs;
    }

    public HashMap<Integer, Integer> getFinalCards() {
        return finalCards;
    }

    public HashMap<Integer, Integer> getTwoFlush() {
        return twoFlush;
    }

    public boolean atLeastOneLineIsStillGoing() {
        for (Line line : lines) {
            if (line.isStillGoing())
                return true;
        }
        return false;
    }

    public void askForGuess(int cardNumber) throws IOException, InterruptedException, URISyntaxException {
        System.out.println("Guess is made? " + guessIsMade);
        int autowins = 0;
        int stillGoing = 0;
        for (Line line : lines) {
            if (line.isAutoWin())
                autowins++;
            else if (line.isStillGoing())
                stillGoing++;
        }
        System.out.println("Autowins " + autowins);
        if (stillGoing == 0 && autowins > 0) {
            System.out.println("No need to guess this time. You AUTOWIN!");
            for (Line line : lines) {
                if (line.isStillGoing()) {
                    gui.setAutowin(line.getLineNumber(), cardNumber + 1);
                }
            }
            havePlayerGuessDownColumn(cardNumber, "autowin");
        } else {
            if (autowins > 0) {
                for (Line line : lines) {
                    if (line.isAutoWin()) {
                        gui.setAutowin(line.getLineNumber(), cardNumber + 1);
                    }
                }
            }
            System.out.println("Higher or lower?");

        }
    }

    public void guessFirstCards() throws IOException, InterruptedException, URISyntaxException {
        System.out.println("Guess is made? " + guessIsMade);
        Line thisLine = lines[0];
        Card thisCard = thisLine.getCurrentCard();
        if (thisCard.isTwo() || thisCard.isAce()) {
            System.out.println("AUTOWIN for the column.");
            for (Line line : lines)
                line.setOnAutoWin(true);
            for (int index = 1; index < numberOfLines; index++)
                guesses[index] = "AUTOWIN";
            havePlayerGuessDownColumn(0, "autowin");
        } else {
            askForGuess(0);
        }
    }

    public void guessCorrect(Line line, Card thisCard, Card nextCard) {
        if (cardNumber < 3 && (line.getNextCard().isAce() || line.getNextCard().isTwo()))
            gui.setAutowin(line.getLineNumber(), cardNumber + 2);
        printLineWithCard(line, thisCard, nextCard);
        continueLine(line);
    }

    public void guessWrong(Line line, Card thisCard, Card nextCard) throws IOException, URISyntaxException {
        line.setLost(true);
        printLineWithCard(line, thisCard, nextCard);
        gui.setInactive(line, line.getLineNumber(), cardNumber + 1);
        endLine(line);
    }

    public void guessLower(Line line) throws IOException, URISyntaxException {
        Card thisCard = line.getCurrentCard();
        Card nextCard = line.getNextCard();
        if ((thisCard.isGreaterThan(nextCard)) || (thisCard.sameValueAs(nextCard) && tiesWin))
            guessCorrect(line, thisCard, nextCard);
        else
            guessWrong(line, thisCard, nextCard);
    }

    public void guessHigher(Line line) throws IOException, URISyntaxException {
        Card thisCard = line.getCurrentCard();
        Card nextCard = line.getNextCard();
        if (thisCard.isLessThan(nextCard) || (thisCard.sameValueAs(nextCard) && tiesWin))
            guessCorrect(line, thisCard, nextCard);
        else
            guessWrong(line, thisCard, nextCard);
    }

    public void handleAutoWin(Line line) {
        this.continueLine(line);
    }

    public void havePlayerGuessDownColumn(int cardNumber, String higherOrLower) throws IOException, InterruptedException, URISyntaxException {
        System.out.println("Higher or lower: " + higherOrLower);
        //wait(100000);
        for (int lineNumber = 0; lineNumber < numberOfLines; lineNumber++) {
            Line line = lines[lineNumber];
            if (line.isStillGoing()) {
                Card newCard = line.drawNextCard();
                if (cardNumber == 0 && (line.getCurrentCard().isAce() || line.getCurrentCard().isTwo())) {
                    System.out.println("Autowin first column");
                    if (onlyStartingCardReplace && canReplace) {
                        setCanReplace(false);
                        gui.replaceButton.setVisible(false);
                    }
                }
                if (higherOrLower.equals("replace")) {
                    if (!(line.isAutoWin())) {
                        line.setCurrentCard(newCard);
                        gui.dealCard(newCard, lineNumber, cardNumber);
                    }
                    if (newCard.isAce() || newCard.isTwo()) {
                        line.setToAutoWin();
                        gui.setAutowin(lineNumber, cardNumber + 1);
                    }
                } else {
                    gui.dealCard(newCard, lineNumber, cardNumber + 1);
                    if (line.isAutoWin()) {
                        printLineWithCard(line, line.getCurrentCard(), line.getNextCard());
                        this.handleAutoWin(line);
                    } else {
                        if (higherOrLower.equals("higher"))
                            guessHigher(line);
                        else if (higherOrLower.equals("lower"))
                            guessLower(line);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("guess is " + higherOrLower);
        System.out.println("----------------");
        System.out.println();
        cardNumber++;
        this.cardNumber = cardNumber;
        play(player);
    }

    public void continueLine(Line line) {
        line.continueToNextCard();
        if (line.getStreak() > 3) {
            System.out.println("You win on this line.");
            endLine(line);
        }
        if (line.isOnAutoWin())
            line.setOnAutoWin(false);
    }

    public void endLine(Line line) {
        line.setStillGoing(false);
    }

    public void addCredits(MultiLinePlayer p) {
        System.out.println();
        int prize;
        if (getFlushes() == 2 && isActivatePokerBonus()) {
            prize = this.getTwoFlush().get(2) * this.getCredits();
        }
        else if (isExactPair() && isActivatePokerBonus())
            prize = this.getExactPairs().get(this.getStreaksOfFour()) * this.getCredits();
        else if (isColumnPair() && isActivatePokerBonus())
            prize = this.getColumnPairs().get(this.getStreaksOfFour()) * this.getCredits();
        else if (isEndsWithAce() && getStreaksOfFour() == 1 && isActivatePokerBonus())
            prize = this.getFinalCards().get(this.getStreaksOfFour()) * this.getCredits();
        else
            prize = this.getCredits() * this.getPayouts().get(this.getStreaksOfFour());
        p.addCredits(prize);
        gui.resultLabel1.setFont(Font.font("Century Schoolbook", FontPosture.ITALIC, 35));
        gui.resultLabel1.setVisible(true);
        gui.resultLabel1.setWrapText(true);
        if (this.getStreaksOfFour() == 0) {
            System.out.println("You are a total loser.");
            gui.resultLabel1.setStyle("-fx-background-color: red;");
            gui.resultLabel1.setText("GAME OVER");
        } else {
            gui.resultLabel1.setStyle("-fx-background-color: green;");
            gui.resultLabel2.setStyle("-fx-background-color: green;");
            gui.resultLabel2.setWrapText(true);
            if (this.getStreaksOfFour() == 1) {
                System.out.printf("You completed 1 streak, earning %d credits.%n", prize);
            } else {
                System.out.printf("You completed %d streaks, earning %d credits.%n", getStreaksOfFour(), prize);
            }
            if (isActivatePokerBonus() && (flushes > 0 || isExactPair() || isColumnPair() || (isEndsWithAce() && getStreaksOfFour() == 1))) {
                gui.resultLabel0.setFont(Font.font("Century Schoolbook", FontPosture.ITALIC, 35));
                gui.resultLabel0.setStyle("-fx-background-color: yellow;");
                gui.resultLabel0.setStyle("-fx-text-fill: black");
                gui.resultLabel0.setVisible(true);
                gui.resultLabel0.setWrapText(true);
                if (flushes > 1) {
                    gui.resultLabel0.setText(" + " + flushes + "-FLUSH BONUS!");
                }
                else if (isExactPair())
                    gui.resultLabel0.setText(" + FINAL EXACT PAIR BONUS!");
                else if (isColumnPair())
                    gui.resultLabel0.setText(" + FINAL PAIR BONUS!");
                else if (isEndsWithAce())
                    gui.resultLabel0.setText(" + FINAL A/2 BONUS!");
            }
            else {
                gui.resultLabel0.setVisible(false);
            }
            gui.resultLabel1.setText("YOU WIN");
            gui.resultLabel2.setVisible(true);
            gui.resultLabel2.setFont(Font.font("Century Schoolbook", FontPosture.ITALIC, 35));
            gui.resultLabel2.setText(NumberFormat.getIntegerInstance().format(prize) + " CREDITS!");
            setFlushes(0);
            setExactPair(false);
            setColumnPair(false);
            setEndsWithAce(false);
        }
        System.out.printf("You end this game with %d credits.%n", p.getCredits());
        gui.creditButton.setText("Credits: " + p.getCredits());
        cardNumber = 0;
    }

    public void printLine(Line line) {
        StringBuilder sb = new StringBuilder();
        for (Card card : line.getThisDeck().getDrawnCards()) {
            sb.append(String.format("%s%s ", card.getRank(), card.getSuit()));
        }
        sb.append(String.format("%s%s ", line.getCurrentCard().getRank(), line.getCurrentCard().getSuit()));
        if (line.isAutoWin())
            sb.append("AUTOWIN!");
        if (!line.isStillGoing())
            sb.append("X");
        System.out.println(sb);
    }

    public void printLineWithCard(Line line, Card thisCard, Card nextCard) {
        StringBuilder sb = new StringBuilder();
        for (Card card : line.getThisDeck().getDrawnCards()) {
            sb.append(String.format("%s%s ", card.getRank(), card.getSuit()));
        }
        sb.append(String.format("%s%s ", thisCard.getRank(), thisCard.getSuit()));
        if (line.isAutoWin())
            sb.append("AUTOWIN!");
        else {
            sb.append(String.format("%s%s ", nextCard.getRank(), nextCard.getSuit()));
            if (line.isLost())
                sb.append("LOSE.");
        }
        System.out.println(sb);
        System.out.println();
    }


    public void printLines() {
        System.out.println();
        System.out.flush();
        for (int index = 0; index < this.numberOfLines; index++) {
            printLine(lines[index]);
        }
        System.out.println();
    }

    public void play(MultiLinePlayer p) throws IOException, InterruptedException, URISyntaxException {
        System.out.println("card number is " + cardNumber);
        System.out.println("Can replace? " + isCanReplace());
            //while (waiting)
            setGuessIsMade(false);
           // System.out.flush();
           // System.out.println("Welcome to your HOT STREAK game!");
            try {
                System.out.println("enter try loop");
                if (cardNumber == 0) {
                    System.out.println("Welcome to your HOT STREAK game!");

                    Card firstCard = lines[0].drawFirstCard();
                    for (int index = 1; index < this.numberOfLines; index++) {
                        boolean removed = lines[index].removeFirstCard(firstCard);
                        if (!removed) {
                            System.out.println("CARD NOT REMOVED!");
                            System.out.println("We'll have to restart the game.");
                            return;
                        }
                    }
                    System.out.printf("You had %d credits and paid %d credits total for %d lines,%n", p.getCredits(), this.getCredits() * numberOfLines, this.getNumberOfLines());
                    p.addCredits(this.getCredits() * numberOfLines * (-1));
                    System.out.println("so you start the game with " + p.getCredits() + " credits.");
                    gui.creditButton.setText("Credits: " + p.getCredits());
                    System.out.println();
                    gui.drawFirstCard(firstCard);
                }
                boolean stillGoing = atLeastOneLineIsStillGoing();
                if (stillGoing & cardNumber < 5) {
                    guesses = new String[numberOfLines];
                    System.out.println();
                    printLines();
                    System.out.println();

                    if (cardNumber == 0) {
                        System.out.println("Guess is made? " + guessIsMade);
                        guessFirstCards();
                    } else {
                        if (onlyStartingCardReplace) {
                            setCanReplace(false);
                            gui.replaceButton.setVisible(false);
                        }
                        System.out.println("Guess is made? " + guessIsMade);
                        askForGuess(cardNumber);
                    }
                    /*
                    if (atLeastOneLineIsStillGoing()) {
                        cardNumber++;
                    }

                     */ //DELETE?
                }
                else {
                    gui.stopWatchLabel.setText("Time: " + stopwatch.elapsedTime() + " seconds");
                    System.out.println("Stopwatch ends.  Elapsed time: " + stopwatch.elapsedTime());
                    for (Line thisLine : lines) {
                        System.out.println("final card is " + thisLine.getCurrentCard().printCard());
                        if (thisLine.getStreak() > p.getHotStreak())
                            p.setHotStreak(thisLine.getStreak());
                        if (thisLine.getStreak() == 4) {
                            streaksOfFour++;
                            if (thisLine.getCurrentCard().isAce() || thisLine.getCurrentCard().isTwo())
                                setEndsWithAce(true);
                            if (thisLine.isFlush())
                                flushes++;
                        }
                        p.setStreak(thisLine.getStreak());
                    }
                    System.out.println();
                    System.out.println("Game ends with the following streaks:");
                    System.out.println();
                    for (int index = 0; index < numberOfLines; index++) {
                        player.setStreak(lines[index].getStreak());
                        System.out.printf("Line %d - %d%n", index + 1, lines[index].getStreak());
                    }
                    System.out.println();
                    System.out.println();
                    System.out.printf("Streaks of 4 achieved: %d%n", getStreaksOfFour());
                    if (streaksOfFour == 2) { //TODO - if streaksOfFour > 2?
                        if (lines[0].getCurrentCard().isEqualTo(lines[1].getCurrentCard()))
                            setExactPair(true);
                        else if (lines[0].getCurrentCard().sameValueAs(lines[1].getCurrentCard()))
                            setColumnPair(true);
                    }
                    addCredits(p);
                    System.out.println("add credits");
                    streaksOfFour = 0;
                    gui.setWaitingTrue();
                }
            } catch (NullPointerException e) {
                System.out.println("NULL POINTER ERROR");
                return;
            }

    }

    public static class Stopwatch {

        private final long start;

        /**
         * Initializes a new stopwatch.
         */
        public Stopwatch() {
            start = System.currentTimeMillis();
        }


        /**
         * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
         *
         * @return elapsed CPU time (in seconds) since the stopwatch was created
         */
        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }
    }

    public class HotStreakGui extends BorderPane {
        private final GetCard backCard = new GetCard(new Card(0, "B", 'C'));
        private final GetCard inactiveCard = new GetCard(new Card(0, "I", 'B'));
        private final GetCard autowinCard = new GetCard(new Card(0, "W", 'I'));
        private final Image backImage = new Image(getClass().getResource(backCard.getActiveCardImage()).toURI().toString());
        private final Image inactiveBackImage = new Image(getClass().getResource(inactiveCard.getInactiveCardImage()).toURI().toString());
        private final Image autowinImage = new Image(getClass().getResource(autowinCard.getActiveCardImage()).toURI().toString());
        private final GridPane board = new GridPane();
        private final Button higher = new Button("Higher");
        private final Button lower = new Button("Lower");
        private final Button deal = new Button("Deal");
        private final Button creditsMore = new Button("+");
        private final Button creditButton = new Button("Credits: " + player.getCredits());
        private final Button creditsLess = new Button("-");
        private final Button anteButton = new Button("Bet: " + getCredits());
        private final Button tiesLoseBasic = new Button("Basic Ties Lose");
        private final Button tiesWinBasic = new Button("Basic Ties Win");
        private final Button tiesLoseStart = new Button("Starting Card Replacement Ties Lose");
        private final Button tiesWinStart = new Button("Starting Card Replacement Ties Win");
        private final Button tiesLoseAny = new Button("Replace Any Column Ties Lose");
        private final Button tiesWinAny = new Button("Replace Any Column Ties Win");
        private final Button tiesLoseBonus = new Button("2-Line Basic Ties Lose WITH BONUSES");
        private final Button tiesLoseStartBonus = new Button("2-Line Starting Card Replacement"  + System.lineSeparator() + "Ties Lose WITH BONUSES");
        private final Button linesLess = new Button("-");
        private final Button linesButton = new Button("Lines: " + getNumberOfLines());
        private final Button linesMore = new Button("+");
        private final Label streaksListing = new Label();
        private final VBox streaksPanel = new VBox();
        private final Label currentStreaksLabel = new Label();
        private final Label stopWatchLabel = new Label();
        private final VBox resultPanel = new VBox();
        private final Label resultLabel0 = new Label();
        private final Label resultLabel1 = new Label();
        private final Label resultLabel2 = new Label();
        private final VBox replacePanel = new VBox();
        private final Label gameStringLabel = new Label();
        private final Button replaceButton = new Button();

        public HotStreakGui() throws URISyntaxException {
            super();
            this.setCenter(board);
            board.setMinWidth(800);
            board.setMaxWidth(800);
            VBox leftSideBoard = new VBox();
            leftSideBoard.setMinWidth(400);
            leftSideBoard.setMaxWidth(400);

            VBox rulesPanel = new VBox();
            Label patent = new Label("U.S. Patent No. 11,610,456");
            patent.setTextAlignment(TextAlignment.LEFT);
            patent.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
            rulesPanel.getChildren().add(patent);
            Label rules = new Label("RULES");
            rules.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
            rulesPanel.getChildren().add(rules);
            Label version = new Label(System.lineSeparator() + "  -As & 2s Auto-Win" + System.lineSeparator() + "  -Ties Lose");
            version.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            version.setMinHeight(100);
            version.setMaxHeight(100);
            rulesPanel.getChildren().add(version);

            replaceButton.setText("REPLACE COLUMN"); //REPLACEMENT
            replaceButton.setStyle("-fx-background-color: yellow");
            replaceButton.setWrapText(true);
            replaceButton.setPrefSize(175, 100); //FIX THIS

            replaceButton.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            replacePanel.getChildren().add(replaceButton);
            replaceButton.setVisible(false);

            gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "Basic Ties Lose");
            gameStringLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            gameStringLabel.setAlignment(Pos.CENTER);
            gameStringLabel.setMinHeight(80);
            gameStringLabel.setMaxHeight(80);
            replacePanel.getChildren().add(gameStringLabel);


            VBox optionsPanel = new VBox();
            Label Label2 = new Label(""); //REPLACEMENT
            Label2.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
            optionsPanel.getChildren().add(Label2);

            Label finalGameStringLabel = gameStringLabel;
            tiesLoseBasic.setOnAction(e -> {
                setGameString("Basic Ties Lose");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win" + System.lineSeparator() + "  -Ties Lose");
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setDisable(true);
                tiesWinBasic.setDisable(false);
                tiesLoseStart.setDisable(false);
                tiesWinStart.setDisable(false);
                tiesLoseAny.setDisable(false);
                tiesWinAny.setDisable(false);
                tiesLoseBonus.setDisable(false);
                tiesLoseStartBonus.setDisable(false);
                finalGameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "Basic Ties Lose");
                setTiesWin(false);
                setCanReplace(false);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setDisable(false);
                linesButton.setDisable(false);
                linesMore.setDisable(false);
            });

            tiesWinBasic.setOnAction(e -> {
                setTiesWin(true);
                setGameString("Basic Ties Win");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win" + System.lineSeparator() + "  -Ties Win");
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setDisable(false);
                tiesWinBasic.setDisable(true);
                tiesLoseStart.setDisable(false);
                tiesWinStart.setDisable(false);
                tiesLoseAny.setDisable(false);
                tiesWinAny.setDisable(false);
                tiesLoseBonus.setDisable(false);
                tiesLoseStartBonus.setDisable(false);
                gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "Basic Ties Win");
                setCanReplace(false);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setDisable(false);
                linesButton.setDisable(false);
                linesMore.setDisable(false);
            });

            tiesLoseStart.setOnAction(e -> {
                setGameString("Ties Lose Starting Cards");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win "  + System.lineSeparator() + "  -Ties Lose"  + System.lineSeparator());
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setDisable(false);
                tiesWinBasic.setDisable(false);
                tiesLoseStart.setDisable(true);
                tiesWinStart.setDisable(false);
                tiesLoseAny.setDisable(false);
                tiesWinAny.setDisable(false);
                tiesLoseBonus.setDisable(false);
                tiesLoseStartBonus.setDisable(false);
                gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "Starting Card Replacement" + System.lineSeparator() + "Ties Lose");
                setTiesWin(false);
                setCanReplace(true);
                setOnlyStartingCardReplace(true);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setDisable(false);
                linesButton.setDisable(false);
                linesMore.setDisable(false);
            });

            tiesWinStart.setOnAction(e -> {
                setTiesWin(true);
                setGameString("Ties Win Starting Cards");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win "  + System.lineSeparator() + "  -Ties Win"  + System.lineSeparator());
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setDisable(false);
                tiesWinBasic.setDisable(false);
                tiesLoseStart.setDisable(false);
                tiesWinStart.setDisable(true);
                tiesLoseAny.setDisable(false);
                tiesWinAny.setDisable(false);
                tiesLoseBonus.setDisable(false);
                tiesLoseStartBonus.setDisable(false);
                gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "Starting Card Replacement" + System.lineSeparator() + "Ties Win");
                setCanReplace(true);
                setOnlyStartingCardReplace(true);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setDisable(false);
                linesButton.setDisable(false);
                linesMore.setDisable(false);
            });

            tiesLoseAny.setOnAction(e -> {
                setGameString("Ties Lose Any Column");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win "  + System.lineSeparator() + "  -Ties Lose"  + System.lineSeparator());
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setDisable(false);
                tiesWinBasic.setDisable(false);
                tiesLoseStart.setDisable(false);
                tiesWinStart.setDisable(false);
                tiesLoseAny.setDisable(true);
                tiesWinAny.setDisable(false);
                tiesLoseBonus.setDisable(false);
                tiesLoseStartBonus.setDisable(false);
                gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "Replace Any Column"  + System.lineSeparator() + "Ties Lose");
                setTiesWin(false);
                setCanReplace(true);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setDisable(false);
                linesButton.setDisable(false);
                linesMore.setDisable(false);
            });

            tiesWinAny.setOnAction(e -> {
                setTiesWin(true);
                setGameString("Ties Win Any Column");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win "  + System.lineSeparator() + "  -Ties Win"  + System.lineSeparator());
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setDisable(false);
                tiesWinBasic.setDisable(false);
                tiesLoseStart.setDisable(false);
                tiesWinStart.setDisable(false);
                tiesLoseAny.setDisable(false);
                tiesWinAny.setDisable(true);
                tiesLoseBonus.setDisable(false);
                tiesLoseStartBonus.setDisable(false);
                gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "Replace Any Column"  + System.lineSeparator() + "Ties Win");
                setCanReplace(true);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setDisable(false);
                linesButton.setDisable(false);
                linesMore.setDisable(false);
            });

            tiesLoseBonus.setOnAction(e -> {
                setGameString("Ties Lose Bonus");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win "  + System.lineSeparator() + "  -Ties Lose"  + System.lineSeparator() + "  -Poker Bonuses"  + System.lineSeparator() + "Activated");
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                while (getNumberOfLines() > 2)
                    setLinesLess();
                payouts = setPayouts(getNumberOfLines());
                columnPairs = setColumnPairs(getNumberOfLines());
                exactPairs = setExactPairs(getNumberOfLines());
                finalCards = setFinalCards(getNumberOfLines());
                twoFlush = setTwoFlush(getNumberOfLines());
                tiesLoseBasic.setDisable(false);
                tiesWinBasic.setDisable(false);
                tiesLoseStart.setDisable(false);
                tiesWinStart.setDisable(false);
                tiesLoseAny.setDisable(false);
                tiesWinAny.setDisable(false);
                tiesLoseBonus.setDisable(true);
                tiesLoseStartBonus.setDisable(false);
                gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "2-Line Basic Ties Lose"  + System.lineSeparator() + "WITH BONUSES");
                setTiesWin(false);
                setActivatePokerBonus(true);
                setCanReplace(false);
                setOnlyStartingCardReplace(false);
                changePayTable();
                linesLess.setDisable(true);
                linesButton.setDisable(true);
                linesMore.setDisable(true);
            });

            tiesLoseStartBonus.setOnAction(e -> {
                setGameString("Ties Lose Starting Bonus");
                version.setText(System.lineSeparator() + "  -As & 2s Auto-Win "  + System.lineSeparator() + "  -Ties Lose"  + System.lineSeparator() + "  -Poker Bonuses"  + System.lineSeparator() + "Activated");
                version.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                while (getNumberOfLines() > 2)
                    setLinesLess();
                payouts = setPayouts(getNumberOfLines());
                columnPairs = setColumnPairs(getNumberOfLines());
                exactPairs = setExactPairs(getNumberOfLines());
                finalCards = setFinalCards(getNumberOfLines());
                twoFlush = setTwoFlush(getNumberOfLines());
                setActivatePokerBonus(true);
                tiesLoseBasic.setDisable(false);
                tiesWinBasic.setDisable(false);
                tiesLoseStart.setDisable(false);
                tiesWinStart.setDisable(false);
                tiesLoseAny.setDisable(false);
                tiesWinAny.setDisable(false);
                tiesLoseBonus.setDisable(false);
                tiesLoseStartBonus.setDisable(true);
                gameStringLabel.setText("Current Game Setting:" + System.lineSeparator() + "2-Line Starting Card"  + System.lineSeparator() + "Replacement Ties Lose"  + System.lineSeparator() + "WITH BONUSES");
                setTiesWin(false);
                setActivatePokerBonus(true);
                setCanReplace(true);
                setOnlyStartingCardReplace(true);
                changePayTable();
                linesLess.setDisable(true);
                linesButton.setDisable(true);
                linesMore.setDisable(true);
            });

            optionsPanel.getChildren().add(tiesLoseBasic);
            optionsPanel.getChildren().add(tiesWinBasic);
            optionsPanel.getChildren().add(tiesLoseStart);
            optionsPanel.getChildren().add(tiesWinStart);
            optionsPanel.getChildren().add(tiesLoseAny);
            optionsPanel.getChildren().add(tiesWinAny);
            optionsPanel.getChildren().add(tiesLoseBonus);
            optionsPanel.getChildren().add(tiesLoseStartBonus);
            optionsPanel.setSpacing(10);
            //   tiesLoseStartBonus.setFont(Font.font(getFont().getFontName(), Font.PLAIN, 10));
            // tiesLoseStartBonus.setSize(new Dimension(10, 40));
            tiesLoseStartBonus.setTextAlignment(TextAlignment.LEFT);


            leftSideBoard.getChildren().add(rulesPanel);
            leftSideBoard.getChildren().add(replacePanel);
            // leftSideBoard.getChildren().add(gameStringPanel);
            leftSideBoard.getChildren().add(optionsPanel);

            this.setLeft(leftSideBoard);

            for (int i = 0; i < 5; i++) {
                RowConstraints row = new RowConstraints();
                row.setMaxHeight(80);
                row.setMinHeight(80);
                ColumnConstraints column = new ColumnConstraints();
                column.setMaxWidth(80);
                column.setMinWidth(80);
                board.getRowConstraints().add(row);
                board.getColumnConstraints().add(column);
            }

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    ImageView img = new ImageView(backImage);
                    img.setFitHeight(100);
                    img.setFitWidth(100);
                    GridPane.setColumnIndex(img, x);
                    GridPane.setRowIndex(img, y);
                    board.getChildren().add(img);
                }
            }

            board.setHgap(60);
            board.setVgap(60);



            this.setCenter(board);

            VBox rightSideBoard = new VBox();
            rightSideBoard.setMinWidth(500);
            rightSideBoard.setMaxWidth(500);
            rightSideBoard.setSpacing(100);

            Label payouts = new Label("PAYOUTS");
            payouts.setTextAlignment(TextAlignment.CENTER);
            payouts.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
            streaksPanel.getChildren().add(payouts);
            streaksPanel.getChildren().add(streaksListing);

            streaksListing.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            changePayTable();

            VBox currentStreaksPanel = new VBox();
            Label currentLabel = new Label("CURRENT"  + System.lineSeparator() + "STREAKS"  + System.lineSeparator());
            currentLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
            currentLabel.setAlignment(Pos.CENTER);
            currentStreaksPanel.getChildren().add(currentLabel);

            rightSideBoard.getChildren().add(streaksPanel);
            rightSideBoard.getChildren().add(currentStreaksPanel);


            resultLabel0.setText("");
            resultLabel0.setAlignment(Pos.CENTER);
            resultLabel0.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
            resultPanel.getChildren().add(resultLabel0);
            resultLabel0.setStyle("-fx-background-color: green;");
            resultLabel0.setStyle("-fx-text-fill: white");
            resultLabel0.setWrapText(true);
            resultLabel1.setText("");
            resultLabel1.setAlignment(Pos.CENTER);
            resultLabel1.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
            resultLabel1.setWrapText(true);
            resultPanel.getChildren().add(resultLabel1);
            resultLabel1.setStyle("-fx-background-color: green;");
            resultLabel1.setStyle("-fx-text-fill: white");
            resultLabel2.setText("");
            resultLabel2.setTextAlignment(TextAlignment.CENTER);
            resultLabel2.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
            resultPanel.getChildren().add(resultLabel2);
            resultLabel2.setStyle("-fx-background-color: green;");
            resultLabel2.setStyle("-fx-text-fill: white");
            resultLabel2.setWrapText(true);

            currentStreaksPanel.getChildren().add(currentStreaksLabel);
            currentStreaksPanel.getChildren().add(stopWatchLabel);
            currentStreaksPanel.getChildren().add(resultPanel);
            //stopWatchLabel.setBorder(new Border("-fx-padding: 30, 10, 10, 10"));  DELETE?
            stopWatchLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

            this.setRight(rightSideBoard);

            HBox options = new HBox();

            HBox top = new HBox();
            top.setSpacing(30);
            top.setMaxWidth(1000);
            top.setMinWidth(1000);
            top.setMinHeight(30);
            top.setMaxHeight(30);


            this.setTop(top);

            higher.setOnAction(e -> {
                setGuessIsHigher(true);
                setGuessIsMade(true);
                try {
                    havePlayerGuessDownColumn(cardNumber, "higher");
                } catch (IOException | URISyntaxException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                setGuessIsMade(false);
            });
            higher.setDisable(true);

            replaceButton.setOnAction(e -> {
                {
                    System.out.println("REPLACE button pushed");
                    System.out.println("Replace call? " + replaceCall);
                    if (!replaceCall) {
                        System.out.println("Card number " + cardNumber);
                        System.out.println("REPLACE");
                        if (cardNumber == 0) {
                            Card newNewCard = lines[0].drawFirstCard();
                            for (int index = 1; index < getNumberOfLines(); index++) {
                                boolean removed = lines[index].removeFirstCard(newNewCard);
                                if (!removed) {
                                    System.out.println("CARD NOT REMOVED!");
                                    System.out.println("We'll have to restart the game.");
                                    return;
                                }
                            }
                            try {
                                gui.drawFirstCard(newNewCard);
                            } catch (IOException | URISyntaxException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if (canReplace) {
                            System.out.println("should replace");
                            System.out.println("Guess is made" + guessIsMade);
                            try {
                                havePlayerGuessDownColumn(cardNumber, "replace");
                            } catch (IOException | URISyntaxException | InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        System.out.println();
                        System.out.println();
                        printLines();
                        System.out.println();
                        System.out.println();
                        setReplaceCall(false);
                        setGuessIsMade(false);
                        try {
                            askForGuess(cardNumber);
                        } catch (IOException | URISyntaxException | InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    //if (!waiting) {
                        setReplaceCall(true);
                        setGuessIsMade(true);

                        replaceButton.setVisible(false);
                    //}
                }
            });

            options.getChildren().add(higher);

            lower.setOnAction(e -> {
                setGuessIsHigher(false);
                setGuessIsMade(true);
                try {
                    havePlayerGuessDownColumn(cardNumber, "lower");
                } catch (IOException | URISyntaxException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                setGuessIsMade(false);
            });
            lower.setDisable(true);

            options.getChildren().add(lower);

            deal.setMinWidth(50);
            deal.setMaxWidth(50);

            options.getChildren().add(deal);

            creditsLess.setOnAction(e -> {
                if (getCredits() > 1) {
                    setAnteLess();
                    changePayTable();
                }
            });

            options.getChildren().add(creditsLess);
            options.getChildren().add(anteButton);

            creditsMore.setOnAction(e -> {
                if (getCredits() < 5) {
                    setAnteMore();
                    changePayTable();
                }
            });

            options.getChildren().add(creditsMore);

            linesLess.setOnAction(e -> {
                if (getNumberOfLines() > 2) {
                    setLinesLess();
                }
            });

            options.getChildren().add(linesLess);

            options.getChildren().add(linesButton);

            options.getChildren().add(linesMore);

            linesMore.setOnAction(e -> {
                if (getNumberOfLines() < 5) {
                    setLinesMore();
                }
            });

            options.getChildren().add(creditButton);

            options.setSpacing(60);
            options.setMaxWidth(1000);
            options.setMinWidth(1000);


            this.setBottom(options);

            deal.setOnAction(e -> {
                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        System.out.println("should restart");
                        for (Node child : board.getChildren()) {
                            if (board.getRowIndex(child) == x && board.getColumnIndex(child) == y)
                                ((ImageView)child).setImage(backImage);
                            ((ImageView)child).setFitHeight(100);
                            ((ImageView)child).setFitWidth(100);
                        }
                    }
                }
                player.setNumberOfLines(getNumberOfLines());
                setNumberOfLinesStillGoing(getNumberOfLines());
                setUpLines();
                setWaitingFalse();
                setGuessIsMade(false);
                System.out.println("Can replace? " + isCanReplace());
                stopwatch = new Stopwatch();
                stopWatchLabel.setText("");
                try {
                    play(player);
                } catch (IOException | InterruptedException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                currentStreaksLabel.setText(getNumberOfLinesStillGoing() + "");
                currentStreaksLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
            });
        }

        private Image scaleCard(Card card, boolean active) throws URISyntaxException {
            GetCard cardImage = new GetCard(card);
            if (active)
                return new Image(getClass().getResource(cardImage.getActiveCardImage()).toURI().toString());
            else
                return new Image(getClass().getResource(cardImage.getInactiveCardImage()).toURI().toString());
        }

        public void drawFirstCard(Card card) throws IOException, URISyntaxException {
            for (int x = 0; x < numberOfLines; x++) {
                for (Node child : board.getChildren()) {
                    if (board.getRowIndex(child) == x && board.getColumnIndex(child) == 0) {
                        ((ImageView) child).setImage(scaleCard(card, true));
                        System.out.println();
                        System.out.println("Card number " + cardNumber + " on line" + x);
                        System.out.println("Card is the " + card.printCard());
                        System.out.println("get row index " + board.getRowIndex(child));
                        System.out.println("get column index " + board.getColumnIndex(child));
                    }
                        ((ImageView)child).setFitHeight(100);
                    ((ImageView)child).setFitWidth(100);
                }
                System.out.println("Row " + x + " added");
            }
        }

        public void dealCard(Card card, int line, int cardNumber) throws IOException, URISyntaxException {
            for (Node child : board.getChildren()) {
                if (board.getRowIndex(child) == line && board.getColumnIndex(child) == cardNumber) {
                    ((ImageView) child).setImage(scaleCard(card, true));
                    System.out.println();
                    System.out.println("Card number " + cardNumber + " on line" + line);
                    System.out.println("Card is the " + card.printCard());
                    System.out.println("get row index " + board.getRowIndex(child));
                    System.out.println("get column index at card Number" + board.getColumnIndex(child));
                }
                    ((ImageView)child).setFitHeight(100);
                ((ImageView)child).setFitWidth(100);
            }
        }

        public void setCardToInactive(int line, int cardNumber, Card card) throws IOException, URISyntaxException {
            for (Node child : board.getChildren()) {
                if (board.getRowIndex(child) == line && board.getColumnIndex(child) == cardNumber) {
                    ((ImageView) child).setImage(scaleCard(card, false));
                    System.out.println();
                    System.out.println("Card number " + cardNumber + " on line" + line);
                    System.out.println("Card is the inactive" + card.printCard());
                    System.out.println("get row index " + board.getRowIndex(child));
                    System.out.println("get column index " + board.getColumnIndex(child));
                }
                ((ImageView)child).setFitHeight(100);
                ((ImageView)child).setFitWidth(100);
            }
        }

        public void setAutowin(int line, int cardNumber) {
            for (Node child : board.getChildren()) {
                if (board.getRowIndex(child) == line && board.getColumnIndex(child) == cardNumber) {
                    ((ImageView) child).setImage(autowinImage);
                    System.out.println();
                    System.out.println("Card number " + cardNumber + " on line" + line);
                    System.out.println("Card is the autowin");
                    System.out.println("get row index " + board.getRowIndex(child));
                    System.out.println("get column index " + board.getColumnIndex(child));
                }
                ((ImageView)child).setFitHeight(100);
                ((ImageView)child).setFitWidth(100);
            }
            SequentialTransition seqTransition = new SequentialTransition (
                    new PauseTransition(Duration.millis(1000)) // wait a second
            );
            seqTransition.play();
        }

        public void setInactive(Line line, int lineNumber, int cardNumber) throws IOException, URISyntaxException {
            int cardNumber1 = 0;
            if (line.getThisDeck().getDrawnCards().size() > 0) {
                for (Card card : line.getThisDeck().getDrawnCards()) {
                    setCardToInactive(lineNumber, cardNumber1, card);
                    cardNumber1++;
                }
            }
            setCardToInactive(lineNumber, cardNumber1, line.getCurrentCard());
            cardNumber1++;
            setCardToInactive(lineNumber, cardNumber1, line.getNextCard());
            cardNumber1++;
            for (int card = cardNumber + 1; card < 5; card++) {
                for (Node child : board.getChildren()) {
                    if (board.getRowIndex(child) == lineNumber && board.getColumnIndex(child) == card) {
                        ((ImageView) child).setImage(inactiveBackImage);
                        System.out.println();
                        System.out.println("Card number " + cardNumber + " on line" + lineNumber);
                        System.out.println("Card is the inactive card");
                        System.out.println("get row index " + board.getRowIndex(child));
                        System.out.println("get column index " + board.getColumnIndex(child));
                    }
                    ((ImageView)child).setFitHeight(100);
                    ((ImageView)child).setFitWidth(100);
                }
            }
            setNumberOfLinesStillGoing(getNumberOfLinesStillGoing() - 1);
            currentStreaksLabel.setText(getNumberOfLinesStillGoing() + "");
        }

        public void setAnteLess() {
            setCredits(getCredits() - 1);
            anteButton.setText("Bet: " + getCredits());
        }

        public void setAnteMore() {
            setCredits(getCredits() + 1);
            anteButton.setText("Bet: " + getCredits());
        }

        public void setLinesLess() {
            setNumberOfLines(getNumberOfLines() - 1);
            payouts = setPayouts(getNumberOfLines());
            changePayTable();
            linesButton.setText("Lines: " + getNumberOfLines());
        }

        public void setLinesMore() {
            setNumberOfLines(getNumberOfLines() + 1);
            payouts = setPayouts(getNumberOfLines());
            changePayTable();
            linesButton.setText("Lines: " + getNumberOfLines());
        }

        public void changePayTable() {
            VBox payTableTable = new VBox();
            StringBuilder streaks = new StringBuilder();
            for (int streak = 1; streak < payouts.size(); streak++) {
                int creditsToEarn = payouts.get(streak) * getCredits();
                if (streak == 1) {
                    streaks.append("1 STREAK: ");
                    streaks.append(creditsToEarn).append(System.lineSeparator());
                    if (isActivatePokerBonus()) {
                        streaks.append("  ").append("W/Final A/2 Bonus:");
                        streaks.append(" ").append(finalCards.get(streak) * getCredits()).append(System.lineSeparator());
                        streaks.append("  ").append(System.lineSeparator());
                    }
                } else {
                    streaks.append(streak).append(" STREAKS: ").append(creditsToEarn).append(System.lineSeparator());
                    if (isActivatePokerBonus()) {
                        streaks.append("  ").append("W/Final Pair Bonus: ").append(columnPairs.get(streak) * getCredits()).append(System.lineSeparator());
                        streaks.append("  ").append("W/Final Exact Pair Bonus: ").append(
                                exactPairs.get(streak) * getCredits()).append(" "  + System.lineSeparator());
                        streaks.append("  ").append("W/2-Flush Bonus: ").append(
                                NumberFormat.getIntegerInstance().format(
                                        (long) twoFlush.get(streak) * getCredits())).append(System.lineSeparator());
                    }
                }
            }
            if (isActivatePokerBonus())
                streaksListing.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
            else
                streaksListing.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            streaksListing.setText(streaks.toString());
        }

        public void setWaitingFalse() {
            waiting = false;
            higher.setDisable(false);
            lower.setDisable(false);
            deal.setDisable(true);
            setReplaceCall(false);
            deal.setText(getGameString());
            creditsMore.setDisable(true);
            creditsLess.setDisable(true);
            anteButton.setDisable(true);
            linesLess.setDisable(true);
            linesButton.setDisable(true);
            linesMore.setDisable(true);
            tiesLoseBasic.setDisable(true);
            tiesWinBasic.setDisable(true);
            tiesLoseStart.setDisable(true);
            tiesWinStart.setDisable(true);
            tiesLoseAny.setDisable(true);
            tiesWinAny.setDisable(true);
            tiesLoseBonus.setDisable(true);
            tiesLoseStartBonus.setDisable(true);
            resultLabel0.setText(null);
            resultLabel0.setVisible(false);
            resultLabel0.setOpacity(1);
            resultLabel0.setStyle("-fx-background-color: green;");
            resultLabel1.setText(null);
            resultLabel1.setVisible(false);
            resultLabel1.setOpacity(1);
            resultLabel1.setStyle("-fx-background-color: green;");
            resultLabel2.setText(null);
            resultLabel2.setVisible(false);
            resultLabel2.setOpacity(1);
            resultLabel2.setStyle("-fx-background-color: green;");
            System.out.println("Waiting false.  Is can replace? " + isCanReplace());
            if (isCanReplace()) {
                // replaceButton.setVisible(true);
                replaceButton.setVisible(true);
            }
        }

        public void setWaitingTrue() {
            waiting = true;
            higher.setDisable(true);
            lower.setDisable(true);
            deal.setDisable(false);
            deal.setText("Deal");
            creditsMore.setDisable(false);
            creditsLess.setDisable(false);
            anteButton.setDisable(false);
            replaceButton.setVisible(false);
            if (getGameString().equals("Basic Ties Lose")) {
                    tiesLoseBasic.setDisable(true);
                    tiesWinBasic.setDisable(false);
                    tiesLoseStart.setDisable(false);
                    tiesWinStart.setDisable(false);
                    tiesLoseAny.setDisable(false);
                    tiesWinAny.setDisable(false);
                    tiesLoseBonus.setDisable(false);
                    tiesLoseStartBonus.setDisable(false);
                    linesLess.setDisable(false);
                    linesButton.setDisable(false);
                    linesMore.setDisable(false);
                    setCanReplace(false);
                }
                else if (getGameString().equals("Basic Ties Win")) {
                    tiesLoseBasic.setDisable(false);
                    tiesWinBasic.setDisable(true);
                    tiesLoseStart.setDisable(false);
                    tiesWinStart.setDisable(false);
                    tiesLoseAny.setDisable(false);
                    tiesWinAny.setDisable(false);
                    tiesLoseBonus.setDisable(false);
                    tiesLoseStartBonus.setDisable(false);
                    linesLess.setDisable(false);
                    linesButton.setDisable(false);
                    linesMore.setDisable(false);
                    setCanReplace(false);
                }
                else if (getGameString().equals("Ties Lose Starting Cards")) {
                    tiesLoseBasic.setDisable(false);
                    tiesWinBasic.setDisable(false);
                    tiesLoseStart.setDisable(true);
                    tiesWinStart.setDisable(false);
                    tiesLoseAny.setDisable(false);
                    tiesWinAny.setDisable(false);
                    tiesLoseBonus.setDisable(false);
                    tiesLoseStartBonus.setDisable(false);
                    linesLess.setDisable(false);
                    linesButton.setDisable(false);
                    linesMore.setDisable(false);
                    setCanReplace(true);
                    System.out.println("Can replace? " + isCanReplace());
                }
                else if (getGameString().equals("Ties Win Starting Cards")) {
                    tiesLoseBasic.setDisable(false);
                    tiesWinBasic.setDisable(false);
                    tiesLoseStart.setDisable(false);
                    tiesWinStart.setDisable(true);
                    tiesLoseAny.setDisable(false);
                    tiesWinAny.setDisable(false);
                    tiesLoseBonus.setDisable(false);
                    tiesLoseStartBonus.setDisable(false);
                    linesLess.setDisable(false);
                    linesButton.setDisable(false);
                    linesMore.setDisable(false);
                    setCanReplace(true);
                    System.out.println("Can replace? " + isCanReplace());
                }
                else if (getGameString().equals("Ties Lose Any Column")) {
                    tiesLoseBasic.setDisable(false);
                    tiesWinBasic.setDisable(false);
                    tiesLoseStart.setDisable(false);
                    tiesWinStart.setDisable(false);
                    tiesLoseAny.setDisable(true);
                    tiesWinAny.setDisable(false);
                    tiesLoseBonus.setDisable(false);
                    tiesLoseStartBonus.setDisable(false);
                    linesLess.setDisable(false);
                    linesButton.setDisable(false);
                    linesMore.setDisable(false);
                    setCanReplace(true);
                    System.out.println("Can replace? " + isCanReplace());
                }
                else if (getGameString().equals("Ties Win Any Column")) {
                    tiesLoseBasic.setDisable(false);
                    tiesWinBasic.setDisable(false);
                    tiesLoseStart.setDisable(false);
                    tiesWinStart.setDisable(false);
                    tiesLoseAny.setDisable(false);
                    tiesWinAny.setDisable(true);
                    tiesLoseBonus.setDisable(false);
                    tiesLoseStartBonus.setDisable(false);
                    linesLess.setDisable(false);
                    linesButton.setDisable(false);
                    linesMore.setDisable(false);
                    setCanReplace(true);
                }
                else if (getGameString().equals("Ties Lose Bonus")) {
                    tiesLoseBasic.setDisable(false);
                    tiesWinBasic.setDisable(false);
                    tiesLoseStart.setDisable(false);
                    tiesWinStart.setDisable(false);
                    tiesLoseAny.setDisable(false);
                    tiesWinAny.setDisable(false);
                    tiesLoseBonus.setDisable(true);
                    tiesLoseStartBonus.setDisable(false);
                    linesLess.setDisable(true);
                    linesButton.setDisable(true);
                    linesMore.setDisable(true);
                    setCanReplace(false);
                }
                else if (getGameString().equals("Ties Lose Starting Bonus")) {
                    tiesLoseBasic.setDisable(false);
                    tiesWinBasic.setDisable(false);
                    tiesLoseStart.setDisable(false);
                    tiesWinStart.setDisable(false);
                    tiesLoseAny.setDisable(false);
                    tiesWinAny.setDisable(false);
                    tiesLoseBonus.setDisable(false);
                    tiesLoseStartBonus.setDisable(true);
                    linesLess.setDisable(true);
                    linesButton.setDisable(true);
                    linesMore.setDisable(true);
                    setCanReplace(true);
                }
            }
        }
    }