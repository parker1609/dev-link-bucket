import {LinksService, TagsService} from "@/common/api.service";
import {FETCH_LINKS, FETCH_TAGS} from "./actions.type";
import {FETCH_END, FETCH_START, SET_TAGS, UPDATE_LINK_IN_LIST} from "./mutations.type";

const state = {
    tags: [],
    links: [],
    isLoading: true,
    linksCount: 0
};

const getters = {
    linksCount(state) {
        return state.linksCount;
    },
    links(state) {
        return state.links;
    },
    isLoading(state) {
        return state.isLoading;
    },
    tags(state) {
        return state.tags;
    }
};

const actions = {
    [FETCH_LINKS]({ commit }, params) {
        commit(FETCH_START);
        return LinksService.query(params.type, params.filters)
            .then(({ data }) => {
                commit(FETCH_END, data);
            })
            .catch(error => {
                throw new Error(error);
            });
    },
    [FETCH_TAGS]({ commit }) {
        return TagsService.get()
            .then(({ data }) => {
                commit(SET_TAGS, data.tags);
            })
            .catch(error => {
                throw new Error(error);
            });
    }
};

/* eslint no-param-reassign: ["error", { "props": false }] */
const mutations = {
    [FETCH_START](state) {
        state.isLoading = true;
    },
    [FETCH_END](state, { links, linksCount }) {
        state.links = links;
        state.linksCount = linksCount;
        state.isLoading = false;
    },
    [SET_TAGS](state, tags) {
        state.tags = tags;
    },
    [UPDATE_LINK_IN_LIST](state, data) {
        state.links = state.links.map(link => {
            if (link.title !== data.title) {
                return link;
            }
            // We could just return data, but it seems dangerous to
            // mix the results of different api calls, so we
            // protect ourselves by copying the information.
            // article.favorited = data.favorited;
            // article.favoritesCount = data.favoritesCount;
            return link;
        });
    }
};

export default {
    state,
    getters,
    actions,
    mutations
};
