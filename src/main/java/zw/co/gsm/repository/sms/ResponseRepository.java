package zw.co.gsm.repository.sms;

import org.springframework.data.repository.CrudRepository;
import zw.co.gsm.domain.sms.ServerResponse;


/**
 * Created by ezinzombe on 3/8/18.
 */
public interface ResponseRepository extends CrudRepository<ServerResponse, Long> {
}
