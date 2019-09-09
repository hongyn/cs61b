public class Palindrome {
    /** Given a String, wordToDeque should return a Deque where the characters
     * appear in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++){
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Return true if the given word is a palindrome, and false otherwise.
     * palindrome is the word that is the same whether it is read forwards or backwards.
     */
    /** recursive method using wordToDeque */
    public boolean isPalindrome(String word){
        Deque<Character> leftWord = wordToDeque(word);
        if (leftWord.size() == 1 || leftWord.size() == 0){
            return true;
        }
        else if(leftWord.removeFirst() == leftWord.removeLast()){
            return isPalindrome(word.substring(1, (word.length() - 1)));
        }
        else
            return false;
    }
    /** Another method
    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = new ArrayDeque<>();
        String reverseWord = "";
        for (int i = (word.length() - 1); i >= 0; i--){
            reverseWord += word.charAt(i);
        }
        return reverseWord.equals(word);
    }
     */

    /** Return true if the word is a palindrome according to the character comparison test
     * provided by the CharacterComparator passed in as argument cc.
     */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> leftWord = wordToDeque(word);
        if (leftWord.size() == 1 || leftWord.size() == 0){
            return true;
        }
        else if(cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))){
            return isPalindrome(word.substring(1, (word.length() - 1)), cc);
        }
        else
            return false;
    }

}
