package demos;

public class StringsEqual {
	
	public static int mystery(String s) {
	    char[] letters = s.toCharArray();
	    int x = 0;
	    for (int i = 0; i < letters.length; i++) {
	        if (letters[i] == ' ') {
	           letters[i] = '_';
	           x++;
	        }
	    }
	    System.out.println(s);
	    return x;
	}
	
    public static void main(String[] args) {
    	String text = new String("My ");
        System.out.println(text);
        System.out.println(mystery("test strings and spaces"));
        String s = "%one%%two%%%three%%%%";
        String[] sa = s.split("[one,two,three]");
        for (int i = 0; i < sa.length; i++) {
        	System.out.println(sa[i]);
        }
    }

}
