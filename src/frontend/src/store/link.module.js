import Vue from "vue";
import {LinksService,} from "@/common/api.service";
import {
    FETCH_LINK,
    LINK_EDIT,
    LINK_EDIT_ADD_TAG,
    LINK_EDIT_REMOVE_TAG,
    LINK_PUBLISH,
    LINK_RESET_STATE
} from "./actions.type";
import {RESET_STATE, SET_LINK, TAG_ADD, TAG_REMOVE,} from "./mutations.type";

const initialState = {
    link: {
        uri: "",
        title: "",
        tags: []
    },
};

export const state = { ...initialState };

export const actions = {
    async [FETCH_LINK](context, linkSlug, prevLink) {
        // avoid extronuous network call if article exists
        if (prevLink !== undefined) {
            return context.commit(SET_LINK, prevLink);
        }
        const { data } = await LinksService.get(linkSlug);
        context.commit(SET_LINK, data.link);
        return data;
    },
    [LINK_PUBLISH]({ state }) {
        return LinksService.create(state.link);
    },
    [LINK_EDIT]({ state }) {
        return LinksService.update(state.link.slug, state.link);
    },
    [LINK_EDIT_ADD_TAG](context, tag) {
        context.commit(TAG_ADD, tag);
    },
    [LINK_EDIT_REMOVE_TAG](context, tag) {
        context.commit(TAG_REMOVE, tag);
    },
    [LINK_RESET_STATE]({ commit }) {
        commit(RESET_STATE);
    }
};

/* eslint no-param-reassign: ["error", { "props": false }] */
export const mutations = {
    [SET_LINK](state, link) {
        state.link = link;
    },
    [TAG_ADD](state, tag) {
        state.link.tags = state.link.tags.concat([tag]);
    },
    [TAG_REMOVE](state, tag) {
        state.link.tags = state.link.tags.filter(t => t !== tag);
    },
    [RESET_STATE]() {
        for (let f in state) {
            Vue.set(state, f, initialState[f]);
        }
    }
};

const getters = {
    link(state) {
        return state.link;
    },
};

export default {
    state,
    actions,
    mutations,
    getters
};
