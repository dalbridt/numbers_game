package sub.guessnum;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Data{
    public String suggested = "";
    int [] currentCombination = new int [4];
    int[] score = new int[2];
    int guessed;
    int inPlace;

    Data next;

    public boolean setSuggested(String suggested) {
        if(suggested.length() == 4 && Pattern.matches("[0-9]+", suggested) && !hasDuplicates(suggested)) {
            this.suggested = suggested;
            convertCombination(suggested);
            return true;
        }
        return false;
    }
    public String getSuggested() {
        return suggested;
    }
    public int getGuessed() {
        return guessed;
    }

    public int getInPlace() {
        return inPlace;
    }

    public void setGuessed() {
        this.guessed = this.score[0];
    }

    public void setInPlace(int inPlace) {
        this.inPlace = this.score[1];
    }

    public void convertCombination(String suggested){
        char [] arraySuggested = suggested.toCharArray();
        for(int i = 0; i < 4; i++) {
            this.currentCombination[i] = arraySuggested[i];
        }

    }

    private boolean hasDuplicates(String suggested) {
        HashSet<Character> charSet = new HashSet<>();
        for (char c : suggested.toCharArray()) {
            if (!charSet.add(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean getScore(String suggested, int [] reference){
        convertCombination(suggested);
        boolean flag = true;
        this.score[0] = 0;
        this.score[1] = 0;
        for (int i = 0; i < 4; i++) {
            if (this.currentCombination[i] != reference[i]) {
                flag = false;
            }
            if (isPresent(this.currentCombination[i], reference)) {
                score[0]++;
            }
            if (this.currentCombination[i] == reference[i]) {
                score[1]++;
            }
        }
        return flag;
    }
    public static boolean isPresent(int x, int[] arr) {
        for (int i : arr) {
            if (i == x) {
                return true;
            }
        }
        return false;
    }

}
