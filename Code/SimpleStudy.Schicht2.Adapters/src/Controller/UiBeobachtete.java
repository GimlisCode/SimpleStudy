package Controller;

public interface UiBeobachtete
{
	void registriere(UiBeobachter uiBeobachter);

	void entferne(UiBeobachter uiBeobachter);

	void benachrichtigeUis();
}
