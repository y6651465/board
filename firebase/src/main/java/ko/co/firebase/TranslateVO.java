package ko.co.firebase;

class TranResult {
	   private String translatedText;
	   public String getTranslatedText() {
	      return translatedText;
	   }
	   public void setTranslatedText(String translatedText) {
	      this.translatedText = translatedText;
	   }
	}

	class TranMessage {
	   private TranResult result;
	   public TranResult getResult() {
	      return result;
	   }
	   public void setResult(TranResult result) {
	      this.result = result;
	   }
	}

	public class TranslateVO {
	   private TranMessage message;

	   public TranMessage getMessage() {
	      return message;
	   }

	   public void setMessage(TranMessage message) {
	      this.message = message;
	   }
	}