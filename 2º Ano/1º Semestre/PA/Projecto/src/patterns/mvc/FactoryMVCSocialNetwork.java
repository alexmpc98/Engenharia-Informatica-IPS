package patterns.mvc;

import patterns.mvc.model.SocialNetwork;
import patterns.mvc.controller.SocialNetworkController;
import patterns.mvc.view.SocialNetworkUI;

/**
 * Class to initiate a social network
 */
public class FactoryMVCSocialNetwork {

    public static SocialNetworkUI create()
    {
        SocialNetwork model = new SocialNetwork();
        SocialNetworkUI view = new SocialNetworkUI(model);
        SocialNetworkController controller = new SocialNetworkController(view, model);
        view.setTriggers(controller);
        return view;
    }

}
