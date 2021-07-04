package question1;

public class PatternObservateur extends junit.framework.TestCase {

    public void testNotify() {
        ConcreteSubject list;
        ConcreteObserver observer;

        list = new ConcreteSubject();           // création d'un "observé" constitué d'une liste
        observer = new ConcreteObserver();      // création d'un observateur
        list.addObserver(observer);             // ajouter cet observateur à la liste
        list.insert("il fait beau, ce matin");  // modification de cette liste, l'observateur doit
                                                // (dervrait) être notifié

        // "vérification" :
        assertFalse(observer.senders().empty());                            // elle ne doit pas être vide,
        assertEquals(list, observer.senders().pop());                       // est-ce le bon émetteur ?
        assertEquals("il fait beau, ce matin", observer.arguments().pop()); // le paramètre reçu est-il correct ?
    }

    // une liste, 2 observateurs
    public void test1() {
        question1.ConcreteSubject l1 = new question1.ConcreteSubject();
        question1.ConcreteObserver o1 = new question1.ConcreteObserver();
        question1.ConcreteObserver o2 = new question1.ConcreteObserver();
        l1.addObserver(o1);
        l1.addObserver(o2);
        l1.insert("test");
        l1.insert(" 1 ");
        
        // Checking that the two observers have been notified with the right parametres
        
        // For the first observer
        assertFalse(o1.senders().empty());
        
        // Check sender
        assertEquals(l1, o1.senders().pop());
        assertEquals(l1, o1.senders().pop());
        
        // First argument
        assertEquals(" 1 ", o1.arguments().pop());
        // Second argument
        assertEquals("test", o1.arguments().pop());
        
        // For the second observer
        assertFalse(o2.senders().empty());
        
        // Check sender
        assertEquals(l1, o2.senders().pop());
        assertEquals(l1, o2.senders().pop());
        
        // First argument
        assertEquals(" 1 ", o2.arguments().pop());
        // Second argument
        assertEquals("test", o2.arguments().pop());

        // ne pas modifier ces lignes, dernières assertions vraies de cette
        // méthode
        assertTrue(o1.senders().empty() && o1.arguments().empty());
        assertTrue(o2.senders().empty() && o2.arguments().empty());
    }

    // deux listes, 1 observateur
    public void test2() {
        question1.ConcreteSubject l1 = new question1.ConcreteSubject();
        question1.ConcreteSubject l2 = new question1.ConcreteSubject();

        question1.ConcreteObserver o = new question1.ConcreteObserver();
        l1.addObserver(o);
        l2.addObserver(o);
        l1.insert("testA");
        l1.insert(" A ");
        l2.insert("testB");
        l2.insert(" B ");
        
        // Check that the observer have been notified by the two observables with the right parametres.
        assertFalse(o.senders().empty());
        
        // First sender
        assertEquals(l2, o.senders().pop());
        assertEquals(l2, o.senders().pop());
        
        // Second sender
        assertEquals(l1, o.senders().pop());
        assertEquals(l1, o.senders().pop());
        
        // First argument
        assertEquals(" B ", o.arguments().pop());
        // Second argument
        assertEquals("testB", o.arguments().pop());
        // Third argument
        assertEquals(" A ", o.arguments().pop());
        // Fourth argument
        assertEquals("testA", o.arguments().pop());

        // ne pas modifier cette ligne, dernière assertion vraie de cette
        // méthode
        assertTrue(o.senders().empty() && o.arguments().empty());
    }

    // deux listes, 2 observateurs
    public void test3() {
        question1.ConcreteSubject l1 = new question1.ConcreteSubject();
        question1.ConcreteSubject l2 = new question1.ConcreteSubject();
        question1.ConcreteObserver o1 = new question1.ConcreteObserver();
        question1.ConcreteObserver o2 = new question1.ConcreteObserver();
        l1.addObserver(o1);
        l1.addObserver(o2);
        l2.addObserver(o1);
        l2.addObserver(o2);

        // Checking that two methods countObservers(), and deleteObserver() are working as expected
        
        // For the first observable
        assertEquals(2, l1.countObservers());
        // For the second observable
        assertEquals(2, l2.countObservers());

        // Delete the observer o1 from the two observables
        l1.deleteObserver(o1);
        l2.deleteObserver(o1);
        
        // Check countObservers()
        assertEquals(1, l1.countObservers());
        assertEquals(1, l2.countObservers());
        
        // Delete the observer o2 from the two observables
        l1.deleteObserver(o2);
        l2.deleteObserver(o2);
    
        // Check countObservers()
        assertEquals(0, l1.countObservers());
        assertEquals(0, l2.countObservers());

        // ne pas modifier ces lignes, dernières assertions vraies de cette
        // méthode
        assertTrue(o1.senders().empty());
        assertTrue(o2.senders().empty());
        assertTrue(l1.countObservers() == 0);
        assertTrue(l2.countObservers() == 0);
    }
    
}
