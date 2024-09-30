package telran.net.games.model;

import java.time.LocalDate;

import org.json.JSONObject;

public record GamerDto(String username,LocalDate birthdata)
{
	private static final String USERNAME_FIELD = "username";
	private static final String BIRTHDATA_FIELD = "birthdata";
	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(USERNAME_FIELD, username);
		jsonObject.put(BIRTHDATA_FIELD, birthdata.toString());
		return jsonObject.toString();
	}
	public GamerDto(JSONObject jsonObject) {
		this(jsonObject.getString(USERNAME_FIELD), LocalDate.parse(jsonObject.getString(BIRTHDATA_FIELD)));
	}
}
