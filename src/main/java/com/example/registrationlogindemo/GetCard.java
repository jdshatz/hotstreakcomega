package com.example.registrationlogindemo;

import java.util.HashMap;

public class GetCard {
	private final Card card;
	private final HashMap<String, String> activeCardMap;
	private final HashMap<String, String> inactiveCardMap;

	public GetCard(Card card) {
		this.card = card;
		this.activeCardMap = setUpActiveHashMap();
		this.inactiveCardMap = setUpInactiveHashMap();
	}
	
	private HashMap<String, String> setUpActiveHashMap() {
		HashMap<String, String> cardMap = new HashMap<>();
		cardMap.put("2C", "/Cards/Card_2Clubs.png");
		cardMap.put("2D", "/Cards/Card_2Diamonds.png");
		cardMap.put("2H", "/Cards/Card_2Hearts.png");
		cardMap.put("2S", "/Cards/Card_2Spades.png");
		cardMap.put("3C", "/Cards/Card_3Clubs.png");
		cardMap.put("3D", "/Cards/Card_3Diamonds.png");
		cardMap.put("3H", "/Cards/Card_3Hearts.png");
		cardMap.put("3S", "/Cards/Card_3Spades.png");
		cardMap.put("4C", "/Cards/Card_4Clubs.png");
		cardMap.put("4D", "/Cards/Card_4Diamonds.png");
		cardMap.put("4H", "/Cards/Card_4Hearts.png");
		cardMap.put("4S", "/Cards/Card_4Spades.png");
		cardMap.put("5C", "/Cards/Card_5Clubs.png");
		cardMap.put("5D", "/Cards/Card_5Diamonds.png");
		cardMap.put("5H", "/Cards/Card_5Hearts.png");
		cardMap.put("5S", "/Cards/Card_5Spades.png");
		cardMap.put("6C", "/Cards/Card_6Clubs.png");
		cardMap.put("6D", "/Cards/Card_6Diamonds.png");
		cardMap.put("6H", "/Cards/Card_6Hearts.png");
		cardMap.put("6S", "/Cards/Card_6Spades.png");
		cardMap.put("7C", "/Cards/Card_7Clubs.png");
		cardMap.put("7D", "/Cards/Card_7Diamonds.png");
		cardMap.put("7H", "/Cards/Card_7Hearts.png");
		cardMap.put("7S", "/Cards/Card_7Spades.png");
		cardMap.put("8C", "/Cards/Card_8Clubs.png");
		cardMap.put("8D", "/Cards/Card_8Diamonds.png");
		cardMap.put("8H", "/Cards/Card_8Hearts.png");
		cardMap.put("8S", "/Cards/Card_8Spades.png");
		cardMap.put("9C", "/Cards/Card_9Clubs.png");
		cardMap.put("9D", "/Cards/Card_9Diamonds.png");
		cardMap.put("9H", "/Cards/Card_9Hearts.png");
		cardMap.put("9S", "/Cards/Card_9Spades.png");
		cardMap.put("10C", "/Cards/Card_10Clubs.png");
		cardMap.put("10D", "/Cards/Card_10Diamonds.png");
		cardMap.put("10H", "/Cards/Card_10Hearts.png");
		cardMap.put("10S", "/Cards/Card_10Spades.png");
		cardMap.put("JC", "/Cards/Card_JackClubs.png");
		cardMap.put("JD", "/Cards/Card_JackDiamonds.png");
		cardMap.put("JH", "/Cards/Card_JackHearts.png");
		cardMap.put("JS", "/Cards/Card_JackSpades.png");
		cardMap.put("QC", "/Cards/Card_QueenClubs.png");
		cardMap.put("QD", "/Cards/Card_QueenDiamonds.png");
		cardMap.put("QH", "/Cards/Card_QueenHearts.png");
		cardMap.put("QS", "/Cards/Card_QueenSpades.png");
		cardMap.put("KC", "/Cards/Card_KingClubs.png");
		cardMap.put("KD", "/Cards/Card_KingDiamonds.png");
		cardMap.put("KH", "/Cards/Card_KingHearts.png");
		cardMap.put("KS", "/Cards/Card_KingSpades.png");
		cardMap.put("AC", "/Cards/Card_AceClubs.png");
		cardMap.put("AD", "/Cards/Card_AceDiamonds.png");
		cardMap.put("AH", "/Cards/Card_AceHearts.png");
		cardMap.put("AS", "/Cards/Card_AceSpades.png");
		cardMap.put("WI", "/Cards/AutoWinCard.png");
		cardMap.put("BC", "/CardsInactive2/back-of-card-active.png");
		return cardMap;
	}

	private HashMap<String, String> setUpInactiveHashMap(){
		HashMap<String, String> cardMap = new HashMap<>();
		cardMap.put("2C", "/CardsInactive2/Card_2Clubs-Inactive.png");
		cardMap.put("2D", "/CardsInactive2/Card_2Diamonds-Inactive.png");
		cardMap.put("2H", "/CardsInactive2/Card_2Hearts-Inactive.png");
		cardMap.put("2S", "/CardsInactive2/Card_2Spades-Inactive.png");
		cardMap.put("3C", "/CardsInactive2/Card_3Clubs-Inactive.png");
		cardMap.put("3D", "/CardsInactive2/Card_3Diamonds-Inactive.png");
		cardMap.put("3H", "/CardsInactive2/Card_3Hearts-Inactive.png");
		cardMap.put("3S", "/CardsInactive2/Card_3Spades-Inactive.png");
		cardMap.put("4C", "/CardsInactive2/Card_4Clubs-Inactive.png");
		cardMap.put("4D", "/CardsInactive2/Card_4Diamonds-Inactive.png");
		cardMap.put("4H", "/CardsInactive2/Card_4Hearts-Inactive.png");
		cardMap.put("4S", "/CardsInactive2/Card_4Spades-Inactive.png");
		cardMap.put("5C", "/CardsInactive2/Card_5Clubs-Inactive.png");
		cardMap.put("5D", "/CardsInactive2/Card_5Diamonds-Inactive.png");
		cardMap.put("5H", "/CardsInactive2/Card_5Hearts-Inactive.png");
		cardMap.put("5S", "/CardsInactive2/Card_5Spades-Inactive.png");
		cardMap.put("6C", "/CardsInactive2/Card_6Clubs-Inactive.png");
		cardMap.put("6D", "/CardsInactive2/Card_6Diamonds-Inactive.png");
		cardMap.put("6H", "/CardsInactive2/Card_6Hearts-Inactive.png");
		cardMap.put("6S", "/CardsInactive2/Card_6Spades-Inactive.png");
		cardMap.put("7C", "/CardsInactive2/Card_7Clubs-Inactive.png");
		cardMap.put("7D", "/CardsInactive2/Card_7Diamonds-Inactive.png");
		cardMap.put("7H", "/CardsInactive2/Card_7Hearts-Inactive.png");
		cardMap.put("7S", "/CardsInactive2/Card_7Spades-Inactive.png");
		cardMap.put("8C", "/CardsInactive2/Card_8Clubs-Inactive.png");
		cardMap.put("8D", "/CardsInactive2/Card_8Diamonds-Inactive.png");
		cardMap.put("8H", "/CardsInactive2/Card_8Hearts-Inactive.png");
		cardMap.put("8S", "/CardsInactive2/Card_8Spades-Inactive.png");
		cardMap.put("9C", "/CardsInactive2/Card_9Clubs-Inactive.png");
		cardMap.put("9D", "/CardsInactive2/Card_9Diamonds-Inactive.png");
		cardMap.put("9H", "/CardsInactive2/Card_9Hearts-Inactive.png");
		cardMap.put("9S", "/CardsInactive2/Card_9Spades-Inactive.png");
		cardMap.put("10C", "/CardsInactive2/Card_10Clubs-Inactive.png");
		cardMap.put("10D", "/CardsInactive2/Card_10Diamonds-Inactive.png");
		cardMap.put("10H", "/CardsInactive2/Card_10Hearts-Inactive.png");
		cardMap.put("10S", "/CardsInactive2/Card_10Spades-Inactive.png");
		cardMap.put("JC", "/CardsInactive2/Card_JackClubs-Inactive.png");
		cardMap.put("JD", "/CardsInactive2/Card_JackDiamonds-Inactive.png");
		cardMap.put("JH", "/CardsInactive2/Card_JackHearts-Inactive.png");
		cardMap.put("JS", "/CardsInactive2/Card_JackSpades-Inactive.png");
		cardMap.put("QC", "/CardsInactive2/Card_QueenClubs-inactive.png");
		cardMap.put("QD", "/CardsInactive2/Card_QueenDiamonds-inactive.png");
		cardMap.put("QH", "/CardsInactive2/Card_QueenHearts-inactive.png");
		cardMap.put("QS", "/CardsInactive2/Card_QueenSpades-Inactive.png");
		cardMap.put("KC", "/CardsInactive2/Card_KingClubs-Inactive.png");
		cardMap.put("KD", "/CardsInactive2/Card_KingDiamonds-inactive.png");
		cardMap.put("KH", "/CardsInactive2/Card_KingHearts-inactive.png");
		cardMap.put("KS", "/CardsInactive2/Card_KingSpades-inactive.png");
		cardMap.put("AC", "/CardsInactive2/Card_AceClubs-Inactive.png");
		cardMap.put("AD", "/CardsInactive2/Card_AceDiamonds-Inactive.png");
		cardMap.put("AH", "/CardsInactive2/Card_AceHearts-Inactive.png");
		cardMap.put("AS", "/CardsInactive2/Card_AceSpades-Inactive.png");
		cardMap.put("IB", "/CardsInactive2/Back-of-card-inactive.png");
		return cardMap;
	}
	
	public String getActiveCardImage() {
		return activeCardMap.get(card.printCard());
	}
	
	public String getInactiveCardImage() {
		return inactiveCardMap.get(card.printCard());
	}
}
