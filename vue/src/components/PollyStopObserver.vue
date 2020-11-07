<template>
    <div></div>
</template>

<script>
    export default {
        name: 'PollyStopObserver',
        data: function() {
            return {
                observer: null,
            };
        },
        mounted: function() {
            let option = {
                rootMargin: '25% 0px -120% 0px',
            };
            this.observer = new IntersectionObserver(([entry]) => {
                if (entry && entry.isIntersecting) {
                    this.$emit('intersect');
                }
            }, option);
            
            this.observer.observe(this.$el);
        }
    }
</script>