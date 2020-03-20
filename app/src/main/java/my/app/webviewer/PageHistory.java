package my.app.webviewer;

import java.util.ArrayList;
import java.util.ListIterator;

class PageHistory {

    private static PageHistory pageHistory = null;
    private ArrayList <String> pages = new ArrayList <String>();
    private ListIterator listIterator = pages.listIterator();
    private String pagedUrl = "";
    private String activeUrl = "";

    private PageHistory() {
    }

    public static PageHistory getPageHistory() {
        if (pageHistory == null) {
            pageHistory = new PageHistory();
        }
        return(pageHistory);
    }

    public void addPreviousSite(String url, String currentUrl) {
        String s;
        listIterator = pages.listIterator(pages.size());

        /* Jos pagedUrl on jokin muu kuin listan viimeinen osoite, tyhjennetään sen edessä olevat osoitteet*/
        while (listIterator.hasPrevious()) {
            s = (String) listIterator.previous();
            if (s.matches(pagedUrl)) {
                while(listIterator.hasNext()) {
                    listIterator.next();
                    listIterator.remove();
                }
                pages.add(pagedUrl);
                pagedUrl = "";
                activeUrl = currentUrl;
                return;
            }
        }
        activeUrl = currentUrl;

        /*Jos sivuhistoriassa on jo kymmenen sivua, poistetaan vanhin sivu*/
        if (pages.size() >= 10) {
            pages.remove(0);
            pages.add(url);
        }
        else {
            pages.add(url);
        }
    }

    public String getPreviousSite() {
        String url;
        listIterator = pages.listIterator(pages.size());
        if (pagedUrl.matches("")) {
            if (listIterator.hasPrevious()) {
                url = (String) listIterator.previous();
                pagedUrl = url;
                return(url);
            } else {
                url = "";
                return(url);
            }
        }
        else {
            while (listIterator.hasPrevious()) {
                url = (String) listIterator.previous();
                if (url.matches(pagedUrl)) {
                    if (listIterator.hasPrevious()) {
                        url = (String) listIterator.previous();
                        pagedUrl = url;
                        return (url);
                    }
                    else {
                        url = "";
                        return(url);
                    }
                }
            }
        }
        return("");
    }

    public String getNextSite() {
        String url;
        listIterator = pages.listIterator(pages.size());
        if (pagedUrl.matches("")) {
            url = "";
            return(url);
        }
        else {
            while (listIterator.hasPrevious()) {
                url = (String) listIterator.previous();
                if (url.matches(pagedUrl)) {
                    if (listIterator.hasNext()) {
                        url = (String) listIterator.next();
                        if (url.matches(pagedUrl)) {
                            if (listIterator.hasNext()) {
                                url = (String) listIterator.next();
                            }
                            else {
                                pagedUrl = "";
                                return(activeUrl);
                            }
                        }
                        pagedUrl = url;
                        return(url);
                    }
                }
            }
        }
        return("");
    }
}
