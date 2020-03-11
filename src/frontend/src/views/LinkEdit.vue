<template>
    <div class="editor-page">
        <div class="container page">
            <div class="row">
                <div class="col-md-10 offset-md-1 col-xs-12">
                    <RwvListErrors :errors="errors"/>
                    <form @submit.prevent="onPublish(link.id)">
                        <fieldset :disabled="inProgress">
                            <fieldset class="form-group">
                                <input
                                        type="text"
                                        class="form-control form-control-lg"
                                        v-model="link.uri"
                                        placeholder="Article URI"
                                />
                            </fieldset>
                            <fieldset class="form-group">
                                <input
                                        type="text"
                                        class="form-control"
                                        v-model="link.title"
                                        placeholder="What's this link about?"
                                />
                            </fieldset>
                            <fieldset class="form-group">
                                <input
                                        type="text"
                                        class="form-control"
                                        v-model="link.type"
                                        placeholder="What's this link type?"
                                />
                            </fieldset>
                            <fieldset class="form-group">
                                <input
                                        type="text"
                                        class="form-control"
                                        placeholder="Enter tags"
                                        v-model="tagInput"
                                        @keypress.enter.prevent="addTag(tagInput)"
                                />
                                <div class="tag-list">
                  <span
                          class="tag-default tag-pill"
                          v-for="(tag, index) of link.tags"
                          :key="tag + index"
                  >
                    <i class="ion-close-round" @click="removeTag(tag)"> </i>
                    {{ tag }}
                  </span>
                                </div>
                            </fieldset>
                        </fieldset>
                        <button
                                :disabled="inProgress"
                                class="btn btn-lg pull-xs-right btn-primary"
                                type="submit"
                        >
                            Publish Link
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    import store from "@/store";
    import RwvListErrors from "@/components/ListErrors";
    import {
        FETCH_LINK,
        LINK_EDIT,
        LINK_EDIT_ADD_TAG,
        LINK_EDIT_REMOVE_TAG,
        LINK_PUBLISH,
        LINK_RESET_STATE
    } from "@/store/actions.type";

    export default {
        name: "RwvLinkEdit",
        components: {RwvListErrors},
        props: {
            previousLink: {
                type: Object,
                required: false
            }
        },
        async beforeRouteUpdate(to, from, next) {
            // Reset state if user goes from /editor/:id to /editor
            // The component is not recreated so we use to hook to reset the state.
            await store.dispatch(LINK_RESET_STATE);
            return next();
        },
        async beforeRouteEnter(to, from, next) {
            // SO: https://github.com/vuejs/vue-router/issues/1034
            // If we arrive directly to this url, we need to fetch the article
            await store.dispatch(LINK_RESET_STATE);
            if (to.params.title !== undefined) {
                await store.dispatch(
                    FETCH_LINK,
                    to.params.title,
                    to.params.previousLink
                );
            }
            return next();
        },
        async beforeRouteLeave(to, from, next) {
            await store.dispatch(LINK_RESET_STATE);
            next();
        },
        data() {
            return {
                tagInput: null,
                inProgress: false,
                errors: {}
            };
        },
        computed: {
            ...mapGetters(["link"])
        },
        methods: {
            onPublish(slug) {
                console.log(slug);
                let action = slug ? LINK_EDIT : LINK_PUBLISH;
                this.inProgress = true;
                this.$store
                    .dispatch(action)
                    .then(({data}) => {
                        console.log(data);
                        this.inProgress = false;
                        this.$router.push({
                            name: "home",
                        });
                    })
                    .catch(({response}) => {
                        this.inProgress = false;
                        this.errors = response.data.errors;
                    });
            },
            removeTag(tag) {
                this.$store.dispatch(LINK_EDIT_REMOVE_TAG, tag);
            },
            addTag(tag) {
                this.$store.dispatch(LINK_EDIT_ADD_TAG, tag);
                this.tagInput = null;
            }
        }
    };
</script>
