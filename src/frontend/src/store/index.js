import Vue from "vue";
import Vuex from "vuex";

import home from "./home.module";
import link from "./link.module";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        home,
        link,
    }
});
