package eniro.search.healthchecks;

import com.codahale.metrics.health.HealthCheck;

import eniro.search.EniroAPISearch;

public class EniroAPIHealthCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
//		if(!EniroAPISearch.isWorking()){
//			return Result.unhealthy("Status not OK.");
//		}
		return Result.healthy();
	}

}
