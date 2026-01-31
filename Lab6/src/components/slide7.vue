<script setup>
const warn = () => {
alert('Bạn vừa ấn Enter!');
};
</script>

<template>
<div>
<button @click.ctrl="warn">A</button>
</div>
</template>