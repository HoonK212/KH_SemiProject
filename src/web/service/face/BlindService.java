package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Blind;

public interface BlindService {

	public Blind getParam(HttpServletRequest req);

	public void applyBlind(Blind blind, int menuno);


}
