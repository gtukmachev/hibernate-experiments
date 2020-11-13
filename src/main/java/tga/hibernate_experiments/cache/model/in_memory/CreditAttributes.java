package tga.hibernate_experiments.cache.model.in_memory;

import java.util.Map;

public class CreditAttributes {

    private Map<String, Integer> parsedXML;
       
    private int totalMortgageAccounts;
    private int openMortgageAccounts;
    private int closedMortgageAccounts;
    private int totalAutoLoanAccounts;
    private int openAutoLoanAccounts;
    private int closedAutoLoanAccounts;
    private int totalCreditCardAccounts;
    private int openCreditCardAccounts;
    private int closedCreditCardAccounts;
    private int totalStudentLoanAccounts;
    private int openStudentLoanAccounts;
    private int closedStudentLoanAccounts;
    private int totalPersonalLoanAccounts;
    private int openPersonalLoanAccounts;
    private int closedPersonalLoanAccounts;
    private int totalOtherAccounts;
    private int openOtherAccounts;
    private int closedOtherAccounts;
    private int collectionAccountsExcludingMedical;
    private int totalMortgageBalance;
    private int totalAutoLoansBalance;
    private int totalCreditCardsBalance;
    private int totalStudentLoansBalance;
    private int totalPersonalLoansBalance;
    private int totalOtherAccountsBalance;
    private int totalCollectionsBalanceExcludingMedical;
    private int totalHomeEquityLoanAccounts;
    private int openHomeEquityLoanAccounts;
    private int closedHomeEquityLoanAccounts;
    private int totalHomeEquityLoansBalance;
    private int totalLineofCreditAccounts;
    private int openLineofCreditAccounts;
    private int closedLineofCreditAccounts;
    private int totalLineofCreditBalance;

    private int resolvedAttrsNumber = 0;
    private static final int ATTRS_NUMBER = 34;
    private boolean notResolved_totalMortgageAccounts                   = true;
    private boolean notResolved_openMortgageAccounts                    = true;
    private boolean notResolved_closedMortgageAccounts                  = true;
    private boolean notResolved_totalAutoLoanAccounts                   = true;
    private boolean notResolved_openAutoLoanAccounts                    = true;
    private boolean notResolved_closedAutoLoanAccounts                  = true;
    private boolean notResolved_totalCreditCardAccounts                 = true;
    private boolean notResolved_openCreditCardAccounts                  = true;
    private boolean notResolved_closedCreditCardAccounts                = true;
    private boolean notResolved_totalStudentLoanAccounts                = true;
    private boolean notResolved_openStudentLoanAccounts                 = true;
    private boolean notResolved_closedStudentLoanAccounts               = true;
    private boolean notResolved_totalPersonalLoanAccounts               = true;
    private boolean notResolved_openPersonalLoanAccounts                = true;
    private boolean notResolved_closedPersonalLoanAccounts              = true;
    private boolean notResolved_totalOtherAccounts                      = true;
    private boolean notResolved_openOtherAccounts                       = true;
    private boolean notResolved_closedOtherAccounts                     = true;
    private boolean notResolved_collectionAccountsExcludingMedical      = true;
    private boolean notResolved_totalMortgageBalance                    = true;
    private boolean notResolved_totalAutoLoansBalance                   = true;
    private boolean notResolved_totalCreditCardsBalance                 = true;
    private boolean notResolved_totalStudentLoansBalance                = true;
    private boolean notResolved_totalPersonalLoansBalance               = true;
    private boolean notResolved_totalOtherAccountsBalance               = true;
    private boolean notResolved_totalCollectionsBalanceExcludingMedical = true;
    private boolean notResolved_totalHomeEquityLoanAccounts             = true;
    private boolean notResolved_openHomeEquityLoanAccounts              = true;
    private boolean notResolved_closedHomeEquityLoanAccounts            = true;
    private boolean notResolved_totalHomeEquityLoansBalance             = true;
    private boolean notResolved_totalLineofCreditAccounts               = true;
    private boolean notResolved_openLineofCreditAccounts                = true;
    private boolean notResolved_closedLineofCreditAccounts              = true;
    private boolean notResolved_totalLineofCreditBalance                = true;
    

    public CreditAttributes(Map<String, Integer> parsedXML) {
        this.parsedXML = parsedXML;
    }

    private void rm() {
        if (resolvedAttrsNumber >= ATTRS_NUMBER) parsedXML = null;
    }

    
    public int getTotalMortgageAccounts()                   { if (notResolved_totalMortgageAccounts)                  { totalMortgageAccounts                   = parsedXML.get("totalMortgageAccounts");                   notResolved_totalMortgageAccounts                   = false; resolvedAttrsNumber++; rm(); } return totalMortgageAccounts;                   }
    public int getOpenMortgageAccounts()                    { if (notResolved_openMortgageAccounts)                   { openMortgageAccounts                    = parsedXML.get("openMortgageAccounts");                    notResolved_openMortgageAccounts                    = false; resolvedAttrsNumber++; rm(); } return openMortgageAccounts;                    }
    public int getClosedMortgageAccounts()                  { if (notResolved_closedMortgageAccounts)                 { closedMortgageAccounts                  = parsedXML.get("closedMortgageAccounts");                  notResolved_closedMortgageAccounts                  = false; resolvedAttrsNumber++; rm(); } return closedMortgageAccounts;                  }
    public int getTotalAutoLoanAccounts()                   { if (notResolved_totalAutoLoanAccounts)                  { totalAutoLoanAccounts                   = parsedXML.get("totalAutoLoanAccounts");                   notResolved_totalAutoLoanAccounts                   = false; resolvedAttrsNumber++; rm(); } return totalAutoLoanAccounts;                   }
    public int getOpenAutoLoanAccounts()                    { if (notResolved_openAutoLoanAccounts)                   { openAutoLoanAccounts                    = parsedXML.get("openAutoLoanAccounts");                    notResolved_openAutoLoanAccounts                    = false; resolvedAttrsNumber++; rm(); } return openAutoLoanAccounts;                    }
    public int getClosedAutoLoanAccounts()                  { if (notResolved_closedAutoLoanAccounts)                 { closedAutoLoanAccounts                  = parsedXML.get("closedAutoLoanAccounts");                  notResolved_closedAutoLoanAccounts                  = false; resolvedAttrsNumber++; rm(); } return closedAutoLoanAccounts;                  }
    public int getTotalCreditCardAccounts()                 { if (notResolved_totalCreditCardAccounts)                { totalCreditCardAccounts                 = parsedXML.get("totalCreditCardAccounts");                 notResolved_totalCreditCardAccounts                 = false; resolvedAttrsNumber++; rm(); } return totalCreditCardAccounts;                 }
    public int getOpenCreditCardAccounts()                  { if (notResolved_openCreditCardAccounts)                 { openCreditCardAccounts                  = parsedXML.get("openCreditCardAccounts");                  notResolved_openCreditCardAccounts                  = false; resolvedAttrsNumber++; rm(); } return openCreditCardAccounts;                  }
    public int getClosedCreditCardAccounts()                { if (notResolved_closedCreditCardAccounts)               { closedCreditCardAccounts                = parsedXML.get("closedCreditCardAccounts");                notResolved_closedCreditCardAccounts                = false; resolvedAttrsNumber++; rm(); } return closedCreditCardAccounts;                }
    public int getTotalStudentLoanAccounts()                { if (notResolved_totalStudentLoanAccounts)               { totalStudentLoanAccounts                = parsedXML.get("totalStudentLoanAccounts");                notResolved_totalStudentLoanAccounts                = false; resolvedAttrsNumber++; rm(); } return totalStudentLoanAccounts;                }
    public int getOpenStudentLoanAccounts()                 { if (notResolved_openStudentLoanAccounts)                { openStudentLoanAccounts                 = parsedXML.get("openStudentLoanAccounts");                 notResolved_openStudentLoanAccounts                 = false; resolvedAttrsNumber++; rm(); } return openStudentLoanAccounts;                 }
    public int getClosedStudentLoanAccounts()               { if (notResolved_closedStudentLoanAccounts)              { closedStudentLoanAccounts               = parsedXML.get("closedStudentLoanAccounts");               notResolved_closedStudentLoanAccounts               = false; resolvedAttrsNumber++; rm(); } return closedStudentLoanAccounts;               }
    public int getTotalPersonalLoanAccounts()               { if (notResolved_totalPersonalLoanAccounts)              { totalPersonalLoanAccounts               = parsedXML.get("totalPersonalLoanAccounts");               notResolved_totalPersonalLoanAccounts               = false; resolvedAttrsNumber++; rm(); } return totalPersonalLoanAccounts;               }
    public int getOpenPersonalLoanAccounts()                { if (notResolved_openPersonalLoanAccounts)               { openPersonalLoanAccounts                = parsedXML.get("openPersonalLoanAccounts");                notResolved_openPersonalLoanAccounts                = false; resolvedAttrsNumber++; rm(); } return openPersonalLoanAccounts;                }
    public int getClosedPersonalLoanAccounts()              { if (notResolved_closedPersonalLoanAccounts)             { closedPersonalLoanAccounts              = parsedXML.get("closedPersonalLoanAccounts");              notResolved_closedPersonalLoanAccounts              = false; resolvedAttrsNumber++; rm(); } return closedPersonalLoanAccounts;              }
    public int getTotalOtherAccounts()                      { if (notResolved_totalOtherAccounts)                     { totalOtherAccounts                      = parsedXML.get("totalOtherAccounts");                      notResolved_totalOtherAccounts                      = false; resolvedAttrsNumber++; rm(); } return totalOtherAccounts;                      }
    public int getOpenOtherAccounts()                       { if (notResolved_openOtherAccounts)                      { openOtherAccounts                       = parsedXML.get("openOtherAccounts");                       notResolved_openOtherAccounts                       = false; resolvedAttrsNumber++; rm(); } return openOtherAccounts;                       }
    public int getClosedOtherAccounts()                     { if (notResolved_closedOtherAccounts)                    { closedOtherAccounts                     = parsedXML.get("closedOtherAccounts");                     notResolved_closedOtherAccounts                     = false; resolvedAttrsNumber++; rm(); } return closedOtherAccounts;                     }
    public int getCollectionAccountsExcludingMedical()      { if (notResolved_collectionAccountsExcludingMedical)     { collectionAccountsExcludingMedical      = parsedXML.get("collectionAccountsExcludingMedical");      notResolved_collectionAccountsExcludingMedical      = false; resolvedAttrsNumber++; rm(); } return collectionAccountsExcludingMedical;      }
    public int getTotalMortgageBalance()                    { if (notResolved_totalMortgageBalance)                   { totalMortgageBalance                    = parsedXML.get("totalMortgageBalance");                    notResolved_totalMortgageBalance                    = false; resolvedAttrsNumber++; rm(); } return totalMortgageBalance;                    }
    public int getTotalAutoLoansBalance()                   { if (notResolved_totalAutoLoansBalance)                  { totalAutoLoansBalance                   = parsedXML.get("totalAutoLoansBalance");                   notResolved_totalAutoLoansBalance                   = false; resolvedAttrsNumber++; rm(); } return totalAutoLoansBalance;                   }
    public int getTotalCreditCardsBalance()                 { if (notResolved_totalCreditCardsBalance)                { totalCreditCardsBalance                 = parsedXML.get("totalCreditCardsBalance");                 notResolved_totalCreditCardsBalance                 = false; resolvedAttrsNumber++; rm(); } return totalCreditCardsBalance;                 }
    public int getTotalStudentLoansBalance()                { if (notResolved_totalStudentLoansBalance)               { totalStudentLoansBalance                = parsedXML.get("totalStudentLoansBalance");                notResolved_totalStudentLoansBalance                = false; resolvedAttrsNumber++; rm(); } return totalStudentLoansBalance;                }
    public int getTotalPersonalLoansBalance()               { if (notResolved_totalPersonalLoansBalance)              { totalPersonalLoansBalance               = parsedXML.get("totalPersonalLoansBalance");               notResolved_totalPersonalLoansBalance               = false; resolvedAttrsNumber++; rm(); } return totalPersonalLoansBalance;               }
    public int getTotalOtherAccountsBalance()               { if (notResolved_totalOtherAccountsBalance)              { totalOtherAccountsBalance               = parsedXML.get("totalOtherAccountsBalance");               notResolved_totalOtherAccountsBalance               = false; resolvedAttrsNumber++; rm(); } return totalOtherAccountsBalance;               }
    public int getTotalCollectionsBalanceExcludingMedical() { if (notResolved_totalCollectionsBalanceExcludingMedical){ totalCollectionsBalanceExcludingMedical = parsedXML.get("totalCollectionsBalanceExcludingMedical"); notResolved_totalCollectionsBalanceExcludingMedical = false; resolvedAttrsNumber++; rm(); } return totalCollectionsBalanceExcludingMedical; }
    public int getTotalHomeEquityLoanAccounts()             { if (notResolved_totalHomeEquityLoanAccounts)            { totalHomeEquityLoanAccounts             = parsedXML.get("totalHomeEquityLoanAccounts");             notResolved_totalHomeEquityLoanAccounts             = false; resolvedAttrsNumber++; rm(); } return totalHomeEquityLoanAccounts;             }
    public int getOpenHomeEquityLoanAccounts()              { if (notResolved_openHomeEquityLoanAccounts)             { openHomeEquityLoanAccounts              = parsedXML.get("openHomeEquityLoanAccounts");              notResolved_openHomeEquityLoanAccounts              = false; resolvedAttrsNumber++; rm(); } return openHomeEquityLoanAccounts;              }
    public int getClosedHomeEquityLoanAccounts()            { if (notResolved_closedHomeEquityLoanAccounts)           { closedHomeEquityLoanAccounts            = parsedXML.get("closedHomeEquityLoanAccounts");            notResolved_closedHomeEquityLoanAccounts            = false; resolvedAttrsNumber++; rm(); } return closedHomeEquityLoanAccounts;            }
    public int getTotalHomeEquityLoansBalance()             { if (notResolved_totalHomeEquityLoansBalance)            { totalHomeEquityLoansBalance             = parsedXML.get("totalHomeEquityLoansBalance");             notResolved_totalHomeEquityLoansBalance             = false; resolvedAttrsNumber++; rm(); } return totalHomeEquityLoansBalance;             }
    public int getTotalLineofCreditAccounts()               { if (notResolved_totalLineofCreditAccounts)              { totalLineofCreditAccounts               = parsedXML.get("totalLineofCreditAccounts");               notResolved_totalLineofCreditAccounts               = false; resolvedAttrsNumber++; rm(); } return totalLineofCreditAccounts;               }
    public int getOpenLineofCreditAccounts()                { if (notResolved_openLineofCreditAccounts)               { openLineofCreditAccounts                = parsedXML.get("openLineofCreditAccounts");                notResolved_openLineofCreditAccounts                = false; resolvedAttrsNumber++; rm(); } return openLineofCreditAccounts;                }
    public int getClosedLineofCreditAccounts()              { if (notResolved_closedLineofCreditAccounts)             { closedLineofCreditAccounts              = parsedXML.get("closedLineofCreditAccounts");              notResolved_closedLineofCreditAccounts              = false; resolvedAttrsNumber++; rm(); } return closedLineofCreditAccounts;              }
    public int getTotalLineofCreditBalance()                { if (notResolved_totalLineofCreditBalance)               { totalLineofCreditBalance                = parsedXML.get("totalLineofCreditBalance");                notResolved_totalLineofCreditBalance                = false; resolvedAttrsNumber++; rm(); } return totalLineofCreditBalance;                }
}
