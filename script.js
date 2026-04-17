const navToggle = document.querySelector('.nav-toggle');
const mainNav = document.querySelector('.main-nav');

if (navToggle && mainNav) {
  navToggle.addEventListener('click', () => {
    mainNav.classList.toggle('nav-open');
  });
}

const tabs = document.querySelectorAll('.form-switch .tab');
const authForm = document.querySelector('.auth-form');

if (tabs.length) {
  tabs.forEach((tab, index) => {
    tab.addEventListener('click', () => {
      tabs.forEach((item) => item.classList.remove('active'));
      tab.classList.add('active');
      if (index === 0) {
        authForm.querySelector('button').textContent = 'Iniciar sesión';
      } else {
        authForm.querySelector('button').textContent = 'Registrarse';
      }
    });
  });
}

const thumbButtons = document.querySelectorAll('.gallery-thumbs .thumb');
const mainImage = document.querySelector('.main-image img');

const thumbSources = [
  'zapatos/mujer/mujerRunning1.png',
  'zapatos/mujer/mujerEntrenamiento1.png',
  'zapatos/mujer/mujerCasual1.jpg'
];

thumbButtons.forEach((thumb, index) => {
  thumb.addEventListener('click', () => {
    if (mainImage) {
      mainImage.src = thumbSources[index];
      thumbButtons.forEach((btn) => btn.classList.remove('active'));
      thumb.classList.add('active');
    }
  });
});

const productsFilterPanel = document.querySelector('.filters-panel');
const applyFiltersButton = document.querySelector('#apply-filters');
const clearFiltersButton = document.querySelector('#clear-filters');
const sortSelect = document.querySelector('#sort');
const productCards = document.querySelectorAll('.product-listing .product-card');

if (productsFilterPanel && applyFiltersButton && clearFiltersButton && productCards.length) {
  const categoryCheckboxes = productsFilterPanel.querySelectorAll('input[name="filter-category"]');
  const focusCheckboxes = productsFilterPanel.querySelectorAll('input[name="filter-focus"]');
  const sizeButtons = productsFilterPanel.querySelectorAll('.size-grid button');
  const priceMinInput = productsFilterPanel.querySelector('#price-min');
  const priceMaxInput = productsFilterPanel.querySelector('#price-max');
  const productListing = document.querySelector('.product-listing');
  const productGrid = document.querySelector('.product-grid.product-grid--wide');
  const noResultsMessage = document.createElement('div');
  const originalOrder = Array.from(productCards);

  noResultsMessage.className = 'no-results';
  noResultsMessage.textContent = 'No se encontraron productos con esos filtros.';

  const getRating = (card) => {
    const ratingText = card.querySelector('.rating span')?.textContent || '0';
    return Number(ratingText.replace(',', '.')) || 0;
  };

  const sortProducts = (sortValue) => {
    if (!productGrid) return;
    const cards = Array.from(productGrid.querySelectorAll('.product-card'));
    let sortedCards = cards.slice();

    if (sortValue === 'Precio: menor a mayor') {
      sortedCards.sort((a, b) => Number(a.dataset.price || 0) - Number(b.dataset.price || 0));
    } else if (sortValue === 'Precio: mayor a menor') {
      sortedCards.sort((a, b) => Number(b.dataset.price || 0) - Number(a.dataset.price || 0));
    } else if (sortValue === 'Mejor calificación') {
      sortedCards.sort((a, b) => getRating(b) - getRating(a));
    } else {
      sortedCards = originalOrder;
    }

    sortedCards.forEach((card) => {
      productGrid.appendChild(card);
    });
  };

  const getSelectedCategories = () => Array.from(categoryCheckboxes)
    .filter((input) => input.checked)
    .map((input) => input.value);

  const getSelectedFocuses = () => Array.from(focusCheckboxes)
    .filter((input) => input.checked)
    .map((input) => input.value);

  const getSelectedFiltersFromQuery = () => {
    const params = new URLSearchParams(window.location.search);
    const rawCategory = params.get('category');
    if (!rawCategory) {
      return { categories: [], focuses: [] };
    }

    const values = rawCategory.split(',').map((value) => value.trim()).filter(Boolean);
    const categories = [];
    const focuses = [];

    values.forEach((value) => {
      const categoryMatch = Array.from(categoryCheckboxes).some((input) => input.value.toLowerCase() === value.toLowerCase());
      const focusMatch = Array.from(focusCheckboxes).some((input) => input.value.toLowerCase() === value.toLowerCase());

      if (categoryMatch) {
        categories.push(value);
      } else if (focusMatch) {
        focuses.push(value);
      }
    });

    return { categories, focuses };
  };

  const getPriceRange = () => {
    let minPrice = priceMinInput ? Number(priceMinInput.value) : 0;
    let maxPrice = priceMaxInput && priceMaxInput.value.trim() !== '' ? Number(priceMaxInput.value) : Infinity;

    if (Number.isNaN(minPrice) || minPrice < 0) {
      minPrice = 0;
    }
    if (Number.isNaN(maxPrice) || maxPrice < 0) {
      maxPrice = Infinity;
    }
    if (minPrice > maxPrice) {
      minPrice = 0;
      maxPrice = Infinity;
    }

    return [minPrice, maxPrice];
  };

  const isFilterActive = () => {
    const categoryActive = Array.from(categoryCheckboxes).some((input) => input.checked);
    const focusActive = Array.from(focusCheckboxes).some((input) => input.checked);
    const sizeActive = Array.from(sizeButtons).some((button) => button.classList.contains('active'));
    const minPriceActive = priceMinInput && priceMinInput.value.trim() !== '';
    const maxPriceActive = priceMaxInput && priceMaxInput.value.trim() !== '';
    return categoryActive || focusActive || sizeActive || minPriceActive || maxPriceActive;
  };

  const updateClearButtonState = () => {
    const active = isFilterActive();
    clearFiltersButton.disabled = !active;
    clearFiltersButton.classList.toggle('active', active);
  };

  const isCardMatchingFilters = (card, selectedCategories, selectedFocuses, minPrice, maxPrice) => {
    const cardCategory = card.dataset.category || '';
    const cardFocus = card.dataset.focus || '';
    const cardPrice = Number(card.dataset.price || '0');

    const categoryMatch = selectedCategories.length === 0 || selectedCategories.includes(cardCategory) || (
      cardCategory === 'Unisex' && (selectedCategories.includes('Hombres') || selectedCategories.includes('Mujeres'))
    );
    const focusMatch = selectedFocuses.length === 0 || selectedFocuses.includes(cardFocus);
    const priceMatch = cardPrice >= minPrice && cardPrice <= maxPrice;

    return categoryMatch && focusMatch && priceMatch;
  };

  const hasStockForSize = (size, selectedCategories, selectedFocuses, minPrice, maxPrice) => {
    return Array.from(productCards).some((card) => {
      if (!isCardMatchingFilters(card, selectedCategories, selectedFocuses, minPrice, maxPrice)) {
        return false;
      }

      const stockPairs = (card.dataset.stock || '')
        .split(',')
        .map((pair) => pair.split(':').map((value) => value.trim()))
        .filter((pair) => pair.length === 2)
        .map(([stockSize, stockQty]) => ({
          size: stockSize,
          quantity: Number(stockQty)
        }));

      return stockPairs.some((pair) => pair.size === size && pair.quantity > 0);
    });
  };

  const updateSizeAvailability = () => {
    const selectedCategories = getSelectedCategories();
    const selectedFocuses = getSelectedFocuses();
    const [minPrice, maxPrice] = getPriceRange();

    sizeButtons.forEach((button) => {
      const size = button.textContent.trim();
      const available = hasStockForSize(size, selectedCategories, selectedFocuses, minPrice, maxPrice);
      button.disabled = !available;
      button.classList.toggle('disabled', !available);
      if (!available) {
        button.classList.remove('active');
      }
    });

    updateClearButtonState();
  };

  sizeButtons.forEach((button) => {
    button.addEventListener('click', () => {
      if (button.disabled) {
        return;
      }
      button.classList.toggle('active');
      updateClearButtonState();
    });
  });

  const applyFilters = () => {
    const selectedCategories = Array.from(categoryCheckboxes)
      .filter((input) => input.checked)
      .map((input) => input.value);

    const selectedFocuses = Array.from(focusCheckboxes)
      .filter((input) => input.checked)
      .map((input) => input.value);

    const selectedSizes = Array.from(sizeButtons)
      .filter((button) => button.classList.contains('active'))
      .map((button) => button.textContent.trim());

    let minPrice = priceMinInput ? Number(priceMinInput.value) : 0;
    let maxPrice = priceMaxInput && priceMaxInput.value.trim() !== '' ? Number(priceMaxInput.value) : Infinity;

    if (Number.isNaN(minPrice) || minPrice < 0) {
      minPrice = 0;
    }
    if (Number.isNaN(maxPrice) || maxPrice < 0) {
      maxPrice = Infinity;
    }
    if (minPrice > maxPrice) {
      minPrice = 0;
      maxPrice = Infinity;
    }

    let visibleCount = 0;

    productCards.forEach((card) => {
      const cardCategory = card.dataset.category || '';
      const cardFocus = card.dataset.focus || '';
      const cardSizes = (card.dataset.sizes || '').split(',').map((value) => value.trim());
      const cardPrice = Number(card.dataset.price || '0');

      const categoryMatch = selectedCategories.length === 0 || selectedCategories.includes(cardCategory) || (
        cardCategory === 'Unisex' && (selectedCategories.includes('Hombres') || selectedCategories.includes('Mujeres'))
      );
      const focusMatch = selectedFocuses.length === 0 || selectedFocuses.includes(cardFocus);
      const sizeMatch = selectedSizes.length === 0 || selectedSizes.some((size) => cardSizes.includes(size));
      const priceMatch = cardPrice >= minPrice && cardPrice <= maxPrice;

      const shouldShow = categoryMatch && focusMatch && sizeMatch && priceMatch;
      card.style.display = shouldShow ? '' : 'none';

      if (shouldShow) {
        visibleCount += 1;
      }
    });

    if (productListing) {
      if (visibleCount === 0) {
        if (!productListing.contains(noResultsMessage)) {
          productListing.appendChild(noResultsMessage);
        }
      } else if (productListing.contains(noResultsMessage)) {
        productListing.removeChild(noResultsMessage);
      }
    }
  };

  const clearFilters = () => {
    categoryCheckboxes.forEach((input) => {
      input.checked = false;
    });
    focusCheckboxes.forEach((input) => {
      input.checked = false;
    });
    sizeButtons.forEach((button) => {
      button.classList.remove('active');
    });
    if (priceMinInput) {
      priceMinInput.value = '';
    }
    if (priceMaxInput) {
      priceMaxInput.value = '';
    }
    applyFilters();
    updateSizeAvailability();
    updateClearButtonState();
  };

  const handleSortChange = () => {
    if (sortSelect) {
      sortProducts(sortSelect.value);
    }
  };

  applyFiltersButton.addEventListener('click', () => {
    applyFilters();
    updateSizeAvailability();
    updateClearButtonState();
  });
  clearFiltersButton.addEventListener('click', () => {
    clearFilters();
    updateSizeAvailability();
  });
  if (priceMinInput) {
    priceMinInput.addEventListener('input', () => {
      updateSizeAvailability();
    });
  }
  if (priceMaxInput) {
    priceMaxInput.addEventListener('input', () => {
      updateSizeAvailability();
    });
  }
  categoryCheckboxes.forEach((input) => input.addEventListener('change', () => {
    updateSizeAvailability();
  }));
  focusCheckboxes.forEach((input) => input.addEventListener('change', () => {
    updateSizeAvailability();
  }));

  const applyQueryFilters = () => {
    const { categories, focuses } = getSelectedFiltersFromQuery();
    if (categories.length === 0 && focuses.length === 0) {
      return;
    }

    categories.forEach((value) => {
      const input = Array.from(categoryCheckboxes).find((item) => item.value.toLowerCase() === value.toLowerCase());
      if (input) {
        input.checked = true;
      }
    });

    focuses.forEach((value) => {
      const input = Array.from(focusCheckboxes).find((item) => item.value.toLowerCase() === value.toLowerCase());
      if (input) {
        input.checked = true;
      }
    });

    updateClearButtonState();
    applyFilters();
    updateSizeAvailability();
  };

  if (window.location.search.includes('category=')) {
    applyQueryFilters();
  }

  if (sortSelect) {
    sortSelect.addEventListener('change', handleSortChange);
  }

  updateSizeAvailability();
}

const CART_KEY = 'veltrixCart';

const getCart = () => {
  try {
    return JSON.parse(localStorage.getItem(CART_KEY)) || [];
  } catch {
    return [];
  }
};

const saveCart = (cart) => {
  localStorage.setItem(CART_KEY, JSON.stringify(cart));
};

const formatPrice = (value) => `$${value.toFixed(0)}`;

const updateCartCountDisplay = () => {
  const cartCount = getCart().reduce((total, item) => total + item.quantity, 0);
  document.querySelectorAll('.cart-count').forEach((element) => {
    element.textContent = `${cartCount} artículo${cartCount === 1 ? '' : 's'}`;
  });
  const floatingBadge = document.querySelector('.floating-cart-badge');
  if (floatingBadge) {
    if (cartCount > 0) {
      floatingBadge.textContent = cartCount;
      floatingBadge.style.display = 'grid';
    } else {
      floatingBadge.style.display = 'none';
    }
  }
};

const initFloatingCartButton = () => {
  const currentPage = window.location.pathname.split('/').pop();
  if (currentPage === 'cart.html') return;

  const cartLink = currentPage && currentPage !== 'index.html' && window.location.pathname.includes('/productos/')
    ? '../cart.html'
    : 'cart.html';

  const button = document.createElement('a');
  button.href = cartLink;
  button.className = 'floating-cart-button';
  button.setAttribute('aria-label', 'Ver carrito');
  button.innerHTML = `
    <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
      <path d="M6 6H4V4h2v2zm13.5 1.5l-1.5 9H8l-1-5H5.5V9h2.5l1 5h8l1.5-9h-1.5z" fill="currentColor" opacity="0.8"/>
      <circle cx="9" cy="20" r="1.5" fill="currentColor"/>
      <circle cx="17" cy="20" r="1.5" fill="currentColor"/>
    </svg>
    <span class="floating-cart-badge">0</span>
  `;

  document.body.appendChild(button);
  updateCartCountDisplay();
};

const addToCart = (product) => {
  const cart = getCart();
  const existing = cart.find((item) => item.id === product.id);

  if (existing) {
    existing.quantity += 1;
  } else {
    cart.push({ ...product, quantity: 1 });
  }

  saveCart(cart);
  updateCartCountDisplay();
};

const initProductPageCart = () => {
  const addButton = Array.from(document.querySelectorAll('button.button--primary'))
    .find((button) => button.textContent.trim() === 'Añadir al carrito');

  if (!addButton) return;

  const sizeButtons = document.querySelectorAll('.size-selector button');
  const colorButtons = document.querySelectorAll('.color-swatchs .swatch');
  const quantityInput = document.querySelector('#product-quantity');
  const decreaseButton = document.querySelector('.quantity-decrease');
  const increaseButton = document.querySelector('.quantity-increase');

  const updateAddButton = () => {
    const selectedSize = document.querySelector('.size-selector button.active');
    const selectedColor = document.querySelector('.color-swatchs .swatch.active');
    const quantity = quantityInput ? Number(quantityInput.value) : 0;
    addButton.disabled = !(selectedSize && selectedColor && quantity > 0);
  };

  sizeButtons.forEach((button) => {
    button.classList.remove('active');
    button.addEventListener('click', () => {
      sizeButtons.forEach((btn) => btn.classList.remove('active'));
      button.classList.add('active');
      updateAddButton();
    });
  });

  colorButtons.forEach((button) => {
    button.classList.remove('active');
    button.addEventListener('click', () => {
      colorButtons.forEach((btn) => btn.classList.remove('active'));
      button.classList.add('active');
      updateAddButton();
    });
  });

  if (quantityInput) {
    quantityInput.value = '1';
    quantityInput.addEventListener('input', () => {
      if (quantityInput.value.trim() === '' || Number(quantityInput.value) < 1) {
        quantityInput.value = '1';
      }
      updateAddButton();
    });
  }

  if (decreaseButton && quantityInput) {
    decreaseButton.addEventListener('click', () => {
      const current = Number(quantityInput.value) || 1;
      quantityInput.value = current > 1 ? current - 1 : 1;
      updateAddButton();
    });
  }

  if (increaseButton && quantityInput) {
    increaseButton.addEventListener('click', () => {
      const current = Number(quantityInput.value) || 1;
      quantityInput.value = current + 1;
      updateAddButton();
    });
  }

  updateAddButton();

  addButton.addEventListener('click', () => {
    const title = document.querySelector('h1')?.textContent.trim() || 'Producto';
    const priceText = document.querySelector('.product-price')?.textContent.replace(/\$/g, '').trim() || '0';
    const price = Number(priceText) || 0;
    const image = document.querySelector('.main-image img')?.src || '';
    const category = document.querySelector('.eyebrow')?.textContent.trim() || '';
    const sizeButton = document.querySelector('.size-selector button.active');
    const colorButton = document.querySelector('.color-swatchs .swatch.active');
    const size = sizeButton ? sizeButton.textContent.trim() : '';
    const color = colorButton ? colorButton.getAttribute('aria-label') || '' : '';
    const quantity = quantityInput ? Number(quantityInput.value) || 1 : 1;

    if (!size || !color) {
      return;
    }

    const productId = `${title}-${size}-${color}`;
    addToCart({ id: productId, title, price, image, category, size, color, quantity });

    const originalText = addButton.textContent;
    addButton.textContent = '¡Añadido!';
    addButton.disabled = true;
    setTimeout(() => {
      addButton.textContent = originalText;
      updateAddButton();
    }, 1200);
  });
};

const renderCartPage = () => {
  const cartItemsContainer = document.querySelector('.cart-items');
  const emptyState = document.querySelector('.cart-empty');
  const subtotalElement = document.querySelector('.summary-subtotal');
  const shippingElement = document.querySelector('.summary-shipping');
  const totalElement = document.querySelector('.summary-total-value');
  const payButton = document.querySelector('.summary-card .button--primary.button--full');

  if (!cartItemsContainer || !emptyState || !subtotalElement || !shippingElement || !totalElement || !payButton) {
    return;
  }

  const cart = getCart();
  updateCartCountDisplay();

  if (cart.length === 0) {
    cartItemsContainer.hidden = true;
    emptyState.hidden = false;
    subtotalElement.textContent = '$0';
    shippingElement.textContent = '$0';
    totalElement.textContent = '$0';
    payButton.disabled = true;
    return;
  }

  emptyState.hidden = true;
  cartItemsContainer.hidden = false;
  cartItemsContainer.innerHTML = '';

  let subtotal = 0;

  cart.forEach((item) => {
    subtotal += item.price * item.quantity;

    const cartItem = document.createElement('div');
    cartItem.className = 'cart-item';
    cartItem.innerHTML = `
      <img src="${item.image}" alt="${item.title}" loading="lazy">
      <div class="cart-item-details">
        <h3>${item.title}</h3>
        <p class="product-category">${item.category}${item.size ? ` • Talla ${item.size}` : ''}${item.color ? ` • Color ${item.color}` : ''}</p>
        <div class="quantity-selector">
          <button type="button" data-action="decrease" data-id="${item.id}">-</button>
          <span>${item.quantity}</span>
          <button type="button" data-action="increase" data-id="${item.id}">+</button>
        </div>
      </div>
      <p class="cart-price">${formatPrice(item.price * item.quantity)}</p>
    `;

    cartItemsContainer.appendChild(cartItem);
  });

  const shipping = subtotal > 0 ? 12 : 0;
  subtotalElement.textContent = formatPrice(subtotal);
  shippingElement.textContent = formatPrice(shipping);
  totalElement.textContent = formatPrice(subtotal + shipping);
  payButton.disabled = false;
};

const initCartPage = () => {
  const cartItemsContainer = document.querySelector('.cart-items');

  if (!cartItemsContainer) return;

  cartItemsContainer.addEventListener('click', (event) => {
    const button = event.target.closest('button');
    if (!button) return;

    const action = button.dataset.action;
    const id = button.dataset.id;
    if (!action || !id) return;

    const cart = getCart();
    const itemIndex = cart.findIndex((item) => item.id === id);
    if (itemIndex === -1) return;

    if (action === 'increase') {
      cart[itemIndex].quantity += 1;
    } else if (action === 'decrease') {
      cart[itemIndex].quantity -= 1;
    }

    if (cart[itemIndex].quantity <= 0) {
      cart.splice(itemIndex, 1);
    }

    saveCart(cart);
    renderCartPage();
  });

  renderCartPage();
};

initFloatingCartButton();
initProductPageCart();
initCartPage();
