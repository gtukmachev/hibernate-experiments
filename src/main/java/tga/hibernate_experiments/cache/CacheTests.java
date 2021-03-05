package tga.hibernate_experiments.cache;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import tga.hibernate_experiments.cache.model.Report;
import tga.hibernate_experiments.cache.model.ReportXml;
import tga.hibernate_experiments.utils.HibernateConfiguration;
import tga.hibernate_experiments.utils.TestsWithHibernate;

import java.util.Arrays;

public class CacheTests extends TestsWithHibernate {

    private static final Logger log = LoggerFactory.getLogger(TestsWithHibernate.class);

    public CacheTests() {
        super(
                Arrays.asList(Report.class, ReportXml.class),
                HibernateConfiguration.native_ehcache_2
        );
    }


    private void initData() {
        MDC.put("lp", "init data: ");

        String xml = getRawXml();
        ReportXml reportXml = new ReportXml( xml );
        Report report = new Report( reportXml );


        MDC.put("lp", "");
        commitAndReopenSession();
    }

    private String getRawXml() {
        return
            "{"+
                "\"totalMortgageAccounts\"                  :  1," +
                "\"openMortgageAccounts\"                   :  2," +
                "\"closedMortgageAccounts\"                 :  3," +
                "\"totalAutoLoanAccounts\"                  :  4," +
                "\"openAutoLoanAccounts\"                   :  5," +
                "\"closedAutoLoanAccounts\"                 :  6," +
                "\"totalCreditCardAccounts\"                :  7," +
                "\"openCreditCardAccounts\"                 :  8," +
                "\"closedCreditCardAccounts\"               :  9," +
                "\"totalStudentLoanAccounts\"               : 10," +
                "\"openStudentLoanAccounts\"                : 11," +
                "\"closedStudentLoanAccounts\"              : 12," +
                "\"totalPersonalLoanAccounts\"              : 13," +
                "\"openPersonalLoanAccounts\"               : 14," +
                "\"closedPersonalLoanAccounts\"             : 15," +
                "\"totalOtherAccounts\"                     : 16," +
                "\"openOtherAccounts\"                      : 17," +
                "\"closedOtherAccounts\"                    : 18," +
                "\"collectionAccountsExcludingMedical\"     : 19," +
                "\"totalMortgageBalance\"                   : 20," +
                "\"totalAutoLoansBalance\"                  : 21," +
                "\"totalCreditCardsBalance\"                : 22," +
                "\"totalStudentLoansBalance\"               : 23," +
                "\"totalPersonalLoansBalance\"              : 24," +
                "\"totalOtherAccountsBalance\"              : 25," +
                "\"totalCollectionsBalanceExcludingMedical\": 26," +
                "\"totalHomeEquityLoanAccounts\"            : 27," +
                "\"openHomeEquityLoanAccounts\"             : 28," +
                "\"closedHomeEquityLoanAccounts\"           : 29," +
                "\"totalHomeEquityLoansBalance\"            : 20," +
                "\"totalLineofCreditAccounts\"              : 31," +
                "\"openLineofCreditAccounts\"               : 32," +
                "\"closedLineofCreditAccounts\"             : 33," +
                "\"totalLineofCreditBalance\"               : 34," +
            "}";
    }


    @Test
    public void test1() {
        initData();
    }


}
