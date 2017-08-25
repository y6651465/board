package ko.co.firebase;

public class TransVO {
	   private String text;
	   private String input;
	   private String output;
	   @Override
	   public String toString() {
	      return "TransVO [text=" + text + ", input=" + input + ", output=" + output + "]";
	   }
	   public String getText() {
	      return text;
	   }
	   public void setText(String text) {
	      this.text = text;
	   }
	   public String getInput() {
	      return input;
	   }
	   public void setInput(String input) {
	      this.input = input;
	   }
	   public String getOutput() {
	      return output;
	   }
	   public void setOutput(String output) {
	      this.output = output;
	   }
	
}
