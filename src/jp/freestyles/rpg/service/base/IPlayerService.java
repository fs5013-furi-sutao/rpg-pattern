package jp.freestyles.rpg.service.base;

import jp.freestyles.rpg.status.Status;

public interface IPlayerService {
	void attack(Status status, Status outStatus);
}
