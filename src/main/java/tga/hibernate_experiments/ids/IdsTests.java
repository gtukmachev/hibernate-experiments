package tga.hibernate_experiments.ids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.val;
import tga.hibernate_experiments.ids.domain.Hist;
import tga.hibernate_experiments.ids.domain.Usr;
import tga.hibernate_experiments.utils.HibernateConfiguration;
import tga.hibernate_experiments.utils.TestsWithHibernate;

public class IdsTests extends TestsWithHibernate {
    private static final Logger log = LoggerFactory.getLogger(IdsTests.class);

    public IdsTests() {
        super(Arrays.asList(Usr.class, Hist.class), HibernateConfiguration.no_l2_cache);
    }


    @Test public void createTables() { }

    @Test public void make_two_users() {

        Usr u1 = new Usr(); u1.setName("User 1");
        session.save(u1);

        Usr u2 = new Usr(); u2.setName("User 2");
        session.save(u2);

        List<Hist> hist1 = addHist(u1);
        List<Hist> hist2 = addHist(u2);

        commitAndReopenSession();

        Usr uKey = new Usr(); uKey.setId(u1.getId());
        Hist old = hist1.get(3);
        Hist hKey = new Hist();
        hKey.setUsr( uKey );
        hKey.setMonth( old.getMonth() );

        Hist hDB = session.get(Hist.class, hKey);

        log.info("hDb = {}", hDB);
        log.info("hDb.usr = {}", hDB.getUsr());

        commitAndReopenSession();

        Hist newH = new Hist();
        newH.setUsr(uKey);
        newH.setMonth(old.getMonth());

        newH.setAbc(100);
        newH.setQwe(100);

        session.merge(newH);

        commitAndReopenSession();


    }

    private List<Hist> addHist(Usr u) {
        val histList = new ArrayList<Hist>(12);
        for (int i = 1; i < 13; i++){
            Hist h = new Hist();
            h.setUsr(u);
            h.setMonth(202000+i);
            h.setAbc(i);
            h.setQwe(13-i);
            session.save(h);
            histList.add(h);
        }
        return histList;
    }

}
