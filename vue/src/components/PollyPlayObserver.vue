<template>
    <div></div>
</template>

<script>
    export default {
        name: 'PollyPlayObserver',
        data: function() {
            return {
                observer: null,
            };
        },
        mounted: function() {
            let option = {
                rootMargin: '0% 0px -40% 0px',
            };
            this.observer = new IntersectionObserver(([entry]) => {
                if (entry && entry.isIntersecting) {
                    this.$emit('intersect');
                } else {
                    this.$emit('no-intersect')
                }
            }, option);
            
            this.observer.observe(this.$el);
        }
    }
</script>