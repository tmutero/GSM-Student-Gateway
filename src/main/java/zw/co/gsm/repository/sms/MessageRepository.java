package zw.co.gsm.repository.sms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zw.co.gsm.domain.sms.Message;


import java.util.List;
import java.util.Optional;


/**
 * Created by ezinzombe on 3/8/18.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query("select m from Message m where m.message like :message")
    List<Message> findMessageWithPower(@Param("message") String message);

    @Query("select '*' FROM Message m WHERE lower(m.message) like 'battery' ")
            List<Message> findMessageWithPower();
}
