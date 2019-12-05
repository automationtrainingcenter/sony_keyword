package banking.keyworddriven;

import org.openqa.selenium.By;

public class LocatorHelper {
	static By arg;

	public static By locateElement(String locType, String locValue) {
		locType = locType.toLowerCase();
		switch (locType) {
		case "id":
			arg = By.id(locValue);
			break;
		case "name":
			arg = By.name(locValue);
			break;
		case "cssselector":
			arg = By.cssSelector(locValue);
			break;
		case "xpath":
			arg = By.xpath(locValue);
			break;
		case "linktext":
			arg = By.linkText(locValue);
			break;
		case "partiallinktext":
			arg = By.partialLinkText(locValue);
			break;

		default:
			break;
		}
		return arg;
	}
}
