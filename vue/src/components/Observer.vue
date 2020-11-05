<template>
    <div></div>
</template>

<script>
    export default {
        name: 'observer',
        data: function() {
            return {
                observer: null,
            };
        },
        mounted: function() {
            let option = {
                rootMargin: '0px 0px 200px 0px',
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