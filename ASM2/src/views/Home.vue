<template>
  <!-- ================= BANNER SLIDE ================= -->
  <div class="container-fluid p-0">
    <div id="homeBanner" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item" v-for="(img, index) in banners" :key="img" :class="{ active: index === 0 }">
          <img :src="img" class="d-block w-100 banner-img" />
        </div>
      </div>

      <button class="carousel-control-prev" type="button" data-bs-target="#homeBanner" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </button>

      <button class="carousel-control-next" type="button" data-bs-target="#homeBanner" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
      </button>
    </div>
  </div>

  <!-- ================= GIỚI THIỆU ================= -->
  <div class="container my-5">
    <div class="row">
      <div class="col-md-8">
        <h2 class="mb-3">QTSC News</h2>
        <p>
          QTSC News là nền tảng chia sẻ thông tin, kinh nghiệm và địa điểm hữu ích
          dành cho sinh viên và người học tập, làm việc tại khu QTSC.
        </p>
        <p>
          Website cho phép người dùng đăng bài viết, bình luận, lưu bài yêu thích
          và quản lý nội dung cá nhân một cách trực quan.
        </p>
        <p>
          Với giao diện hiện đại, thân thiện và dễ sử dụng, QTSC News hướng tới
          việc xây dựng một cộng đồng chia sẻ thông tin hiệu quả và đáng tin cậy.
        </p>
      </div>
    </div>
  </div>


</template>

<script setup>
const banners = [
  '/images/qtsc-banner1.jpg',
  '/images/qtsc-banner2.jpg',
  '/images/qtsc-banner3.jpg'
]
</script>

<style scoped>
/* ===== BANNER ===== */
.banner-img {
  height: 420px;
  object-fit: cover;
}

/* ===== ART BOX ===== */
.art-box {

  border-radius: 4px;
}

/* SIZE */
.art-big {
  background: #c86b00;
  height: 360px;
}

.art-wide {
  background: #030e8a;
  height: 160px;
}

.art-small {
  background: #008523;
  height: 180px;
}
</style>
