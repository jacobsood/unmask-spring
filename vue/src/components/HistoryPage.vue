<template>
    <div>
        <!-- This creates a button that navigates user back to the home page -->
        <router-link :to="{ name: 'home'}">Go Back</router-link>
    </div>
</template>

<script>
export default {
    name: 'HistoryPage',
    data: function() {
        return {
            // store the variables here
            // to be used inside the template
            randomData: 1,
        }
    },
    methods: {
        // you methods go here
        // which may be needed in the template
        exampleMethodIncrementsOne: function() {
            this.randomData++;
            // optional return statement if needed by the template
        },
    },
    computed: {
        // values used in the template
        // functions here are reactive - whenever a value is changed, 
        // the template will catch it automatically and update
        // methods here must return something
        getRandomData: function() {
            return this.randomData;
        }
    }
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";

// your style goes in here
// alternative you can create your own scss file and import it like above

</style>